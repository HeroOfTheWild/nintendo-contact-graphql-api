package com.kjam.graphQL.entities;

import java.time.ZonedDateTime;

import com.kjam.graphQL.entities.enums.ContactPurpose;

public record Email(String id, String nintendoId, ContactPurpose purpose, String emailAddress, ZonedDateTime lastModified) { }
