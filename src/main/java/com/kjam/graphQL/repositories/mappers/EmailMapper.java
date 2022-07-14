package com.kjam.graphQL.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kjam.graphQL.entities.Email;
import com.kjam.graphQL.entities.enums.ContactPurpose;

import static com.kjam.graphQL.repositories.mappers.MapperUtils.toZonedDateTime;

public class EmailMapper implements RowMapper<Email>{

    @Override
    public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Email(rs.getString("EMAIL_ID"),
                rs.getString("NINTENDO_ID"),
                ContactPurpose.valueOf(rs.getString("EMAIL_PURPOSE_DC")),
                rs.getString("EMAIL_ADDRESS"),
                toZonedDateTime(rs.getTimestamp("MODIFIED")));
    }
}
