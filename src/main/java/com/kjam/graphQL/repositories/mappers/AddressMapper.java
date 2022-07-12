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
        return Address.builder()
                        .id(rs.getString("ADDRESS_ID"))
                        .nintendoId(rs.getString("NINTENDO_ID"))
                        .purpose(AddressPurpose.valueOf(rs.getString("ADDRESS_PURPOSE_DC")))
                        .country(rs.getString("COUNTRY_CD"))
                        .stateProvince(rs.getString("STATE_PROVINCE_NM"))
                        .cityName(rs.getString("CITY_NM"))
                        .streetAddress(rs.getString("STREET_ADDRESS_LINE_TXT"))
                        .regionCode(rs.getString("REGION_CD"))
                        .postalCode(rs.getString("POSTAL_CD"))
                        .startDate(toLocalDate(rs.getDate("START_DT")))
                        .endDate(toLocalDate(rs.getDate("END_DT")))
                        .lastModified(toZonedDateTime(rs.getTimestamp("MODIFIED")))
                        .build();
    }
    
}
