package com.kjam.graphQL.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.kickstart.spring.error.ThrowableGraphQLError;

@Component
public class GraphQLExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ThrowableGraphQLError handle(EmptyResultDataAccessException e) {
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ThrowableGraphQLError handle(Exception e) {
        return new ThrowableGraphQLError(e);
    }
    
}
