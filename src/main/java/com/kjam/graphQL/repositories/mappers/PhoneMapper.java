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
        return new Phone(rs.getString("PHONE_ID"),
        rs.getString("NINTENDO_ID"),
        PhoneType.valueOf(rs.getString("PHONE_TYPE")),
        ContactPurpose.valueOf(rs.getString("PHONE_PURPOSE_DC")),
        rs.getString("COUNTRY_CD"),
        rs.getString("PHONE_NBR"),
        toZonedDateTime(rs.getTimestamp("MODIFIED")));
    }
    
}
