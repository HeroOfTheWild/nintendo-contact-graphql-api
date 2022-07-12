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
        return Email.builder()
                        .id(rs.getString("EMAIL_ID"))
                        .nintendoId(rs.getString("NINTENDO_ID"))
                        .purpose(ContactPurpose.valueOf(rs.getString("EMAIL_PURPOSE_DC")))
                        .emailAddress(rs.getString("EMAIL_ADDRESS"))
                        .lastModified(toZonedDateTime(rs.getTimestamp("MODIFIED")))
                        .build();
    }
}
