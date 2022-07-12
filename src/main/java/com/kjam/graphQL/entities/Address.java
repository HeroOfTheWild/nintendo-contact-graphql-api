package com.kjam.graphQL.entities;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.kjam.graphQL.entities.enums.AddressPurpose;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String id;
    private String nintendoId;
    private AddressPurpose purpose;
    private String country;
    private String stateProvince;
    private String cityName;
    private String streetAddress;
    private String postalCode;
    private String regionCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private ZonedDateTime lastModified;
}
