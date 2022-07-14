package com.kjam.graphQL.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kjam.graphQL.entities.Address;
import com.kjam.graphQL.repositories.mappers.AddressMapper;

import graphql.relay.DefaultEdge;
import graphql.relay.Edge;

@Repository
public class AddressRepository extends PaginationRepository<Address> {

    public AddressRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    private static final String QUERY_ADDRESS_BY_NINTENDO_ID = 
        "SELECT ADDRESS_ID, NINTENDO_ID, " + 
        "ADDRESS_PURPOSE_DC, COUNTRY_CD, STATE_PROVINCE_NM, CITY_NM, STREET_ADDRESS_LINE_TXT, " + 
        "REGION_CD, POSTAL_CD, START_DT, END_DT, MODIFIED " + 
        "FROM NINTENDO.ADDRESS " + 
        "WHERE NINTENDO_ID = :nintendoId";

    private static final String QUERY_ADDRESS_BY_ID = 
        "SELECT ADDRESS_ID, NINTENDO_ID, " + 
        "ADDRESS_PURPOSE_DC, COUNTRY_CD, STATE_PROVINCE_NM, CITY_NM, STREET_ADDRESS_LINE_TXT, " + 
        "REGION_CD, POSTAL_CD, START_DT, END_DT, MODIFIED " + 
        "FROM NINTENDO.ADDRESS " + 
        "WHERE ADDRESS_ID = :addressId";

    private static final String QUERY_ADDRESS_HST_BY_NINTENDO_ID = 
        "SELECT ADDRESS_ID, NINTENDO_ID, " + 
        "ADDRESS_PURPOSE_DC, COUNTRY_CD, STATE_PROVINCE_NM, CITY_NM, STREET_ADDRESS_LINE_TXT, " + 
        "REGION_CD, POSTAL_CD, START_DT, END_DT, MODIFIED " + 
        "FROM NINTENDO.ADDRESS_HST " + 
        "WHERE NINTENDO_ID = :nintendoId " + 
        "ORDER BY MODIFIED DESC " + 
        "FETCH FIRST :rowsPerPage ROWS ONLY";

    private static final String QUERY_ADDRESS_HST_PAGINATION_BY_NINTENDO_ID = 
        "SELECT ADDRESS_ID, NINTENDO_ID, " + 
        "ADDRESS_PURPOSE_DC, COUNTRY_CD, STATE_PROVINCE_NM, CITY_NM, STREET_ADDRESS_LINE_TXT, " + 
        "REGION_CD, POSTAL_CD, START_DT, END_DT, MODIFIED " + 
        "FROM NINTENDO.ADDRESS_HST " + 
        "WHERE NINTENDO_ID = :nintendoId " + 
        "AND MODIFIED < :startingRow " +
        "ORDER BY MODIFIED DESC " + 
        "FETCH FIRST :rowsPerPage ROWS ONLY";

    public Address retrieve(String addressId) {
        try {
            return jdbcTemplate().queryForObject(QUERY_ADDRESS_BY_ID, 
                new MapSqlParameterSource("addressId", addressId), 
                new AddressMapper());
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Address> retrieveAll(String nintendoId) {
        return jdbcTemplate().query(QUERY_ADDRESS_BY_NINTENDO_ID, 
            new MapSqlParameterSource("nintendoId", nintendoId), 
            new AddressMapper());
    }

    public List<Edge<Address>> edge(List<Address> addresses) {
        return addresses.stream()
                    .map(address -> new DefaultEdge<>(address, toConnectionCursor(address.lastModified())))
                    .collect(Collectors.toList());
    }

	@Override
	public String historyQuery() {
		return QUERY_ADDRESS_HST_BY_NINTENDO_ID;
	}

	@Override
	public String historyPaginationQuery() {
		return QUERY_ADDRESS_HST_PAGINATION_BY_NINTENDO_ID;
	}

	@Override
	public RowMapper<Address> rowMapper() {
		return new AddressMapper();
	}
}
