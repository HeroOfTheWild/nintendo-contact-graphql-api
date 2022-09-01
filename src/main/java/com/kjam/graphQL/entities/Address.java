package com.kjam.graphQL.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.kjam.graphQL.entities.enums.AddressPurpose;

public record Address(String id, String nintendoId, AddressPurpose purpose, String country,
                      String stateProvince, String cityName, String streetAddress,
                      String postalCode, String regionCode, LocalDate startDate,
                      LocalDate endDate, ZonedDateTime lastModified) { 

    public Address addId(UUID uuid) {
        return new Address(uuid.toString(), nintendoId, purpose, 
            country, stateProvince, cityName, 
            streetAddress, postalCode, regionCode, 
            startDate, endDate, lastModified);
    }

    public MapSqlParameterSource map(UUID uuid) {
        return new MapSqlParameterSource()
            .addValue("addressId", uuid.toString())
            .addValue("nintendoId", nintendoId)
            .addValue("purpose", purpose.name())
            .addValue("countryCode", country)
            .addValue("state", stateProvince)
            .addValue("city", cityName)
            .addValue("streetAddress", streetAddress)
            .addValue("regionCode", postalCode)
            .addValue("postalCode", regionCode)
            .addValue("startDate", Date.valueOf(LocalDate.now()))
            .addValue("endDate", null)
            .addValue("modified", Timestamp.valueOf(LocalDateTime.now()));
    }
}
