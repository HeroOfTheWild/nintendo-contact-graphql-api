package com.kjam.graphQL.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kjam.graphQL.entities.Phone;
import com.kjam.graphQL.entities.enums.ContactPurpose;
import com.kjam.graphQL.entities.enums.PhoneType;

import static com.kjam.graphQL.repositories.mappers.MapperUtils.toZonedDateTime;

public class PhoneMapper implements RowMapper<Phone>{

    @Override
    public Phone mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Phone.builder()
        .id(rs.getString("PHONE_ID"))
        .nintendoId(rs.getString("NINTENDO_ID"))
        .type(PhoneType.valueOf(rs.getString("PHONE_TYPE")))
        .purpose(ContactPurpose.valueOf(rs.getString("PHONE_PURPOSE_DC")))
        .countryCode(rs.getString("COUNTRY_CD"))
        .number(rs.getString("PHONE_NBR"))
        .lastModified(toZonedDateTime(rs.getTimestamp("MODIFIED")))
        .build();
    }
    
}
