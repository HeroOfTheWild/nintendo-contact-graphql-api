package com.kjam.graphQL.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Phone;
import com.kjam.graphQL.repositories.PhoneRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PhoneQueryResolver implements GraphQLQueryResolver {

    private final PhoneRepository repository;

    @Async("ResolverThreadPool")
    public CompletableFuture<Phone> phone(String phoneId) {
        return CompletableFuture.completedFuture(repository.retrieve(phoneId));
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Phone>> phones(String nintendoId) {
        return CompletableFuture.completedFuture(repository.retrieveAll(nintendoId));
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Connection<Phone>> phoneHistories(String nintendoId, int rows, String before) {
        return CompletableFuture.completedFuture(repository.retrieveHistory(nintendoId, rows, before));
    }
}
