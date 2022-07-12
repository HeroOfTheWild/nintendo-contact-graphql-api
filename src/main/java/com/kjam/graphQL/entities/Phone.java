package com.kjam.graphQL.entities;

import java.time.ZonedDateTime;

import com.kjam.graphQL.entities.enums.ContactPurpose;
import com.kjam.graphQL.entities.enums.PhoneType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Phone {

    private String id;
    private String nintendoId;
    private PhoneType type;
    private ContactPurpose purpose;
    private String countryCode;
    private String number;
    private ZonedDateTime lastModified;
}
