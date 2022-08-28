package com.kjam.graphQL.resolvers.mutations;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Address;
import com.kjam.graphQL.entities.Email;
import com.kjam.graphQL.entities.Phone;
import com.kjam.graphQL.repositories.AddressRepository;
import com.kjam.graphQL.repositories.EmailRepository;
import com.kjam.graphQL.repositories.PhoneRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final AddressRepository addressRepository;
    private final PhoneRepository   phoneRepository;
    private final EmailRepository   emailRepository;
    
    public String newAddress(Address address) {
        var id = addressRepository.insert(address);
        return Objects.nonNull(id) ? id.toString() : null;
    }

    public String newPhone(Phone phone) {
        var id = phoneRepository.insert(phone);
        return Objects.nonNull(id) ? id.toString() : null;
    }

    public String newEmail(Email email) {
        var id = emailRepository.insert(email);
        return Objects.nonNull(id) ? id.toString() : null;
    }

    public Address issueAddress(Address address) {
        var id = addressRepository.insert(address);
        return address.updateId(id);
    }

    public Phone issuePhone(Phone phone) {
        var id = phoneRepository.insert(phone);
        return phone.addId(id);
    }

    public Email issueEmail(Email email) {
        var id = emailRepository.insert(email);
        return email.addId(id);
    }
}
