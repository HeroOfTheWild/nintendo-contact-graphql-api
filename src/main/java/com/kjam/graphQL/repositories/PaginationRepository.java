package com.kjam.graphQL.repositories;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnection;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class PaginationRepository<T> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PaginationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public abstract List<Edge<T>> edge(List<T> list);
    public abstract String historyQuery();
    public abstract String historyPaginationQuery();
    public abstract RowMapper<T> rowMapper();
    public abstract UUID insert(T t);

    public NamedParameterJdbcTemplate jdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }

    public List<T> retrieveHistory(String nintendoId, int rows) {
        return namedParameterJdbcTemplate.query(historyQuery(), 
            new MapSqlParameterSource("nintendoId", nintendoId)
                .addValue("rowsPerPage", rows), 
            rowMapper());
    }

    public List<T> retrieveHistory(String nintendoId, int rows, Timestamp startingRow) {
        return namedParameterJdbcTemplate.query(historyPaginationQuery(), 
            new MapSqlParameterSource("nintendoId", nintendoId)
                .addValue("rowsPerPage", rows)
                .addValue("startingRow", startingRow), 
            rowMapper());
    }

    public Connection<T> retrieveHistory(String nintendoId, int rows, String cursor) {
        var fetch = rows > 10 ? 10: rows;
        List<T> list = isBlank(cursor) ? retrieveHistory(nintendoId, fetch) : retrieveHistory(nintendoId, fetch, toTimestamp(cursor));
        return createDefaultConnection(edge(list), fetch, cursor);
    }

    private <T> DefaultConnection createDefaultConnection(List<Edge<T>> edges, int rows, String cursor) {
        var pageInfo = new DefaultPageInfo(
            startCursor(edges), 
            endCursor(edges), 
            !isBlank(cursor), 
            edges.size() >= rows);

        return new DefaultConnection<>(edges, pageInfo);
    }

    private boolean isBlank(String value) {
        return Objects.isNull(value) || value.isBlank();
    }

    private <T> ConnectionCursor startCursor(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(0).getCursor();
    }

    private <T> ConnectionCursor endCursor(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(edges.size()-1).getCursor();
    }

    protected ConnectionCursor toConnectionCursor(ZonedDateTime time) {
        return new DefaultConnectionCursor(Base64.getEncoder().encodeToString(longToByte(time.toEpochSecond())));
    }

    protected Timestamp toTimestamp(String cursor) {
        var zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(bytesToLong(Base64.getDecoder().decode(cursor))), ZoneId.systemDefault());
        return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
    }

    private byte[] longToByte(long x) {
        var buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    private long bytesToLong(byte[] bytes) {
        var buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }
    
}
