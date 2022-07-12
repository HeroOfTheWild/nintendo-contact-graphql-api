package com.kjam.graphQL.repositories;

import com.kjam.graphQL.entities.Email;
import com.kjam.graphQL.repositories.mappers.EmailMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import graphql.relay.DefaultEdge;
import graphql.relay.Edge;

@Repository
public class EmailRepository extends PaginationRepository<Email> {

    public EmailRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    private static final String QUERY_EMAIL_BY_NINTENDO_ID = 
        "SELECT EMAIL_ID, NINTENDO_ID, " + 
        "EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED " + 
        "FROM NINTENDO.EMAIL " + 
        "WHERE NINTENDO_ID = :nintendoId";

    private static final String QUERY_EMAIL_BY_ID = 
        "SELECT EMAIL_ID, NINTENDO_ID, " + 
        "EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED " + 
        "FROM NINTENDO.EMAIL " + 
        "WHERE EMAIL_ID = :emailId";

    private static final String QUERY_EMAIL_HST_BY_NINTENDO_ID = 
        "SELECT EMAIL_ID, NINTENDO_ID, " + 
        "EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED " + 
        "FROM NINTENDO.EMAIL_HST " + 
        "WHERE NINTENDO_ID = :nintendoId " + 
        "ORDER BY MODIFIED DESC " + 
        "FETCH FIRST :rowsPerPage ROWS ONLY";

    private static final String QUERY_EMAIL_HST_PAGINATION_BY_NINTENDO_ID = 
        "SELECT EMAIL_ID, NINTENDO_ID, " + 
        "EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED " + 
        "FROM NINTENDO.EMAIL_HST " + 
        "WHERE NINTENDO_ID = :nintendoId " + 
        "AND MODIFIED < :startingRow " +
        "ORDER BY MODIFIED DESC " + 
        "FETCH FIRST :rowsPerPage ROWS ONLY";
    
    public Email retrieve(String emailId) {
        try {
            return jdbcTemplate().queryForObject(QUERY_EMAIL_BY_ID, 
                    new MapSqlParameterSource("emailId", emailId), 
                    new EmailMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Email> retrieveAll(String nintendoId) {
        return jdbcTemplate().query(QUERY_EMAIL_BY_NINTENDO_ID, 
            new MapSqlParameterSource("nintendoId", nintendoId), 
            new EmailMapper());
    }
    
    public List<Edge<Email>> edge(List<Email> emails) {
        return emails.stream()
                    .map(email -> new DefaultEdge<>(email, toConnectionCursor(email.getLastModified())))
                    .collect(Collectors.toList());
    }

    @Override
    public String historyQuery() {
        return QUERY_EMAIL_HST_BY_NINTENDO_ID;
    }

    @Override
    public String historyPaginationQuery() {
        return QUERY_EMAIL_HST_PAGINATION_BY_NINTENDO_ID;
    }

    @Override
    public RowMapper<Email> rowMapper() {
        return new EmailMapper();
    }
}
