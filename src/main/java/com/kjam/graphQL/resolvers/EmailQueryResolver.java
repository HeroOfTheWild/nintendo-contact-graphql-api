package com.kjam.graphQL.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Email;
import com.kjam.graphQL.repositories.EmailRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmailQueryResolver implements GraphQLQueryResolver {

    private final EmailRepository repository;
    
    @Async("ResolverThreadPool")
    public CompletableFuture<List<Email>> emails(String nintendoId) {
        return CompletableFuture.completedFuture(repository.retrieveAll(nintendoId));
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Connection<Email>> emailHistories(String nintendoId, int rows, String before) {
        return CompletableFuture.completedFuture(repository.retrieveHistory(nintendoId, rows, before));
    }
}
