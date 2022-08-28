package com.kjam.graphQL.repositories;

import com.kjam.graphQL.entities.Email;
import com.kjam.graphQL.repositories.mappers.EmailMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private static final String QUERY_EMAIL_BY_NINTENDO_ID = """
        SELECT EMAIL_ID, NINTENDO_ID,   
        EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED   
        FROM NINTENDO.EMAIL   
        WHERE NINTENDO_ID = :nintendoId    
    """;

    private static final String QUERY_EMAIL_BY_ID = """
        SELECT EMAIL_ID, NINTENDO_ID,   
        EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED   
        FROM NINTENDO.EMAIL   
        WHERE EMAIL_ID = :emailId
    """;

    private static final String QUERY_EMAIL_HST_BY_NINTENDO_ID = """
        SELECT EMAIL_ID, NINTENDO_ID,   
        EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED   
        FROM NINTENDO.EMAIL_HST   
        WHERE NINTENDO_ID = :nintendoId   
        ORDER BY MODIFIED DESC   
        FETCH FIRST :rowsPerPage ROWS ONLY
    """;

    private static final String QUERY_EMAIL_HST_PAGINATION_BY_NINTENDO_ID = """
        SELECT EMAIL_ID, NINTENDO_ID,   
        EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED   
        FROM NINTENDO.EMAIL_HST   
        WHERE NINTENDO_ID = :nintendoId   
        AND MODIFIED < :startingRow  
        ORDER BY MODIFIED DESC   
        FETCH FIRST :rowsPerPage ROWS ONLY
    """;

    private static final String QUERY_INSERT_EMAIL = """
        INSERT INTO NINTENDO.EMAIL 
        (EMAIL_ID, NINTENDO_ID, EMAIL_PURPOSE_DC, EMAIL_ADDRESS, MODIFIED)
        VALUES (:emailId, :nintendoId, :purpose, :emailAddress, :modified);
    """;
    
    public Email retrieve(String emailId) {
        return jdbcTemplate().queryForObject(QUERY_EMAIL_BY_ID, 
            new MapSqlParameterSource("emailId", emailId), 
            new EmailMapper());
    }

    public List<Email> retrieveAll(String nintendoId) {
        return jdbcTemplate().query(QUERY_EMAIL_BY_NINTENDO_ID, 
            new MapSqlParameterSource("nintendoId", nintendoId), 
            new EmailMapper());
    }

    public UUID insert(Email email) {
        var uuid = UUID.randomUUID();
        var resultCount = jdbcTemplate().update(QUERY_INSERT_EMAIL, email.map(uuid));
        return resultCount > 0 ? uuid : null;
    }
    
    public List<Edge<Email>> edge(List<Email> emails) {
        return emails.stream()
                    .map(email -> new DefaultEdge<>(email, toConnectionCursor(email.lastModified())))
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
