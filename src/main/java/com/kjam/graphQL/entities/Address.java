package com.kjam.graphQL.entities;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.kjam.graphQL.entities.enums.AddressPurpose;

public record Address(String id, String nintendoId, AddressPurpose purpose, String country,
                      String stateProvince, String cityName, String streetAddress,
                      String postalCode, String regionCode, LocalDate startDate,
                      LocalDate endDate, ZonedDateTime lastModified) { }
