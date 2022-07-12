package com.kjam.graphQL.repositories.mappers;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class MapperUtils {
    
    protected static ZonedDateTime toZonedDateTime(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
            .map(t -> ZonedDateTime.ofInstant(t.toInstant(), ZoneId.systemDefault()))
            .orElse(null);
    }

    protected static LocalDate toLocalDate(Date date) {
        return Optional.ofNullable(date)
        .map(d -> d.toLocalDate())
        .orElse(null);
    }
}
