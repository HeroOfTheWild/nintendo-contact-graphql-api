package com.kjam.graphQL.entities;

import java.time.ZonedDateTime;

import com.kjam.graphQL.entities.enums.ContactPurpose;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {

    private String id;
    private String nintendoId;
    private ContactPurpose purpose;
    private String emailAddress;
    private ZonedDateTime lastModified;
}
