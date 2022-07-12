package com.kjam.graphQL.configurations;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Configuration
public class ScalarConfiguration {

    private final static String NINTENDO_ID_REGEX = "(nin)[0-9]{4}";

    @Bean
    public GraphQLScalarType nintendoId() {
        return GraphQLScalarType.newScalar()
        .name("NintendoId")
        .description("A valid employee id")
        .coercing(new Coercing<String,String>() {

            @Override
            public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                return Optional.ofNullable(dataFetcherResult)
                .filter(String.class::isInstance)
                .map(Object::toString)
                .orElseThrow(() -> new CoercingSerializeException("Unable to serialize object"));
            }

            @Override
            public @NotNull String parseValue(@NotNull Object input) throws CoercingParseValueException {
                if(input instanceof String) {
                    return validateNintendoId((String) input);
                } else {
                    throw new CoercingParseValueException("Nintendo ID input not valid");
                }
            }

            @Override
            public @NotNull String parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                if(input instanceof StringValue) {
                    var id = (StringValue) input;
                    return validateNintendoId(id.getValue());
                } else {
                    throw new CoercingParseLiteralException("Nintendo ID input not valid");
                }
            }
            
        })
        .build();
    }

    protected static String validateNintendoId(final String input) {
        if(input.matches(NINTENDO_ID_REGEX)) {
            return input;
        } else {
            throw new CoercingParseLiteralException("INVALID NINTENDO ID");
        }
    }

    @Bean
    public GraphQLScalarType dateTime() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }
}
