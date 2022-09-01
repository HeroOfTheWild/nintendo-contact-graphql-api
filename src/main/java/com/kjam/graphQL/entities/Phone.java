package com.kjam.graphQL.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.kjam.graphQL.entities.enums.ContactPurpose;
import com.kjam.graphQL.entities.enums.PhoneType;

public record Phone(String id, String nintendoId,
                    PhoneType type, ContactPurpose purpose,
                    String countryCode, String number, ZonedDateTime lastModified) { 

    public Phone addId(UUID uuid) {
        return new Phone(uuid.toString(), nintendoId, type, purpose, countryCode, number, lastModified);
    }

    public MapSqlParameterSource map(UUID uuid) {
        return new MapSqlParameterSource()
            .addValue("phoneId", uuid.toString())
            .addValue("nintendoId", nintendoId)
            .addValue("type", type.name())
            .addValue("purpose", purpose.name())
            .addValue("countryCode", countryCode)
            .addValue("number", number)
            .addValue("modified", Timestamp.valueOf(LocalDateTime.now()));
    }
}
