package com.kjam.graphQL.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.kjam.graphQL.entities.enums.ContactPurpose;

public record Email(String id, String nintendoId, ContactPurpose purpose, String emailAddress, ZonedDateTime lastModified) { 

    public Email addId(UUID uuid) {
        return new Email(uuid.toString(), nintendoId, purpose, emailAddress, lastModified);
    }

    public MapSqlParameterSource map(UUID uuid) {
        return new MapSqlParameterSource()
            .addValue("emailId", uuid.toString())
            .addValue("nintendoId", nintendoId)
            .addValue("purpose", purpose.name())
            .addValue("emailAddress", emailAddress)
            .addValue("modified", Timestamp.valueOf(LocalDateTime.now()));
    }
}
