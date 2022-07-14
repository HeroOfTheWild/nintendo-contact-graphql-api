package com.kjam.graphQL.entities;

import java.time.ZonedDateTime;

import com.kjam.graphQL.entities.enums.ContactPurpose;
import com.kjam.graphQL.entities.enums.PhoneType;

public record Phone(String id, String nintendoId,
                    PhoneType type, ContactPurpose purpose,
                    String countryCode, String number, ZonedDateTime lastModified) { }
