package com.kjam.graphQL.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Address;
import com.kjam.graphQL.repositories.AddressRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AddressQueryResolver implements GraphQLQueryResolver {
    
    private final AddressRepository repository;

    @Async("ResolverThreadPool")
    public CompletableFuture<Address> address(String addressId) {
        return CompletableFuture.completedFuture(repository.retrieve(addressId));
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Address>> addresses(String nintendoId) {
        return CompletableFuture.completedFuture(repository.retrieveAll(nintendoId));
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Connection<Address>> addressHistories(String nintendoId, int rows, String before) {
        return CompletableFuture.completedFuture(repository.retrieveHistory(nintendoId, rows, before));
    }
}
