package com.kjam.graphQL.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kjam.graphQL.entities.Address;
import com.kjam.graphQL.entities.enums.AddressPurpose;

import static com.kjam.graphQL.repositories.mappers.MapperUtils.toZonedDateTime;
import static com.kjam.graphQL.repositories.mappers.MapperUtils.toLocalDate;

public class AddressMapper implements RowMapper<Address>{

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Address(rs.getString("ADDRESS_ID"),
                        rs.getString("NINTENDO_ID"),
                        AddressPurpose.valueOf(rs.getString("ADDRESS_PURPOSE_DC")),
                        rs.getString("COUNTRY_CD"),
                        rs.getString("STATE_PROVINCE_NM"),
                        rs.getString("CITY_NM"),
                        rs.getString("STREET_ADDRESS_LINE_TXT"),
                        rs.getString("POSTAL_CD"),
                        rs.getString("REGION_CD"),
                        toLocalDate(rs.getDate("START_DT")),
                        toLocalDate(rs.getDate("END_DT")),
                        toZonedDateTime(rs.getTimestamp("MODIFIED")));
    }
    
}
