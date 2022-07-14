package com.kjam.graphQL.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kjam.graphQL.entities.Phone;
import com.kjam.graphQL.repositories.mappers.PhoneMapper;

import graphql.relay.DefaultEdge;
import graphql.relay.Edge;

@Repository
public class PhoneRepository extends PaginationRepository<Phone> {

    public PhoneRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    private static final String QUERY_PHONE_BY_NINTENDO_ID = 
        "SELECT PHONE_ID, NINTENDO_ID, " + 
        "PHONE_TYPE, PHONE_PURPOSE_DC, COUNTRY_CD, PHONE_NBR, MODIFIED " + 
        "FROM NINTENDO.PHONE " + 
        "WHERE NINTENDO_ID = :nintendoId";

    private static final String QUERY_PHONE_BY_ID = 
        "SELECT PHONE_ID, NINTENDO_ID, " + 
        "PHONE_TYPE, PHONE_PURPOSE_DC, COUNTRY_CD, PHONE_NBR, MODIFIED " + 
        "FROM NINTENDO.PHONE " + 
        "WHERE PHONE_ID = :phoneId";

    private static final String QUERY_PHONE_HST_BY_NINTENDO_ID = 
        "SELECT PHONE_ID, NINTENDO_ID, " + 
        "PHONE_TYPE, PHONE_PURPOSE_DC, COUNTRY_CD, PHONE_NBR, MODIFIED " + 
        "FROM NINTENDO.PHONE_HST " + 
        "WHERE NINTENDO_ID = :nintendoId " + 
        "ORDER BY MODIFIED DESC " + 
        "FETCH FIRST :rowsPerPage ROWS ONLY";

    private static final String QUERY_PHONE_HST_PAGINATION_BY_NINTENDO_ID = 
        "SELECT PHONE_ID, NINTENDO_ID, " + 
        "PHONE_TYPE, PHONE_PURPOSE_DC, COUNTRY_CD, PHONE_NBR, MODIFIED " + 
        "FROM NINTENDO.PHONE_HST " + 
        "WHERE NINTENDO_ID = :nintendoId " + 
        "AND MODIFIED < :startingRow " +
        "ORDER BY MODIFIED DESC " + 
        "FETCH FIRST :rowsPerPage ROWS ONLY";

    public Phone retrieve(String phoneId) {
        return jdbcTemplate().queryForObject(QUERY_PHONE_BY_ID, 
            new MapSqlParameterSource("phoneId", phoneId), 
            new PhoneMapper());
    }

    public List<Phone> retrieveAll(String nintendoId) {
        return jdbcTemplate().query(QUERY_PHONE_BY_NINTENDO_ID, 
            new MapSqlParameterSource("nintendoId", nintendoId), 
            new PhoneMapper());
    }

    @Override
    public List<Edge<Phone>> edge(List<Phone> phones) {
        return phones.stream()
                    .map(phone -> new DefaultEdge<>(phone, toConnectionCursor(phone.lastModified())))
                    .collect(Collectors.toList());
    }

    @Override
    public String historyQuery() {
        return QUERY_PHONE_HST_BY_NINTENDO_ID;
    }

    @Override
    public String historyPaginationQuery() {
        return QUERY_PHONE_HST_PAGINATION_BY_NINTENDO_ID;
    }

    @Override
    public RowMapper<Phone> rowMapper() {
        return new PhoneMapper();
    }
    
}
