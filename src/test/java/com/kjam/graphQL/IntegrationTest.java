package com.kjam.graphQL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import io.micrometer.core.instrument.util.IOUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
 
    private static final String GRAPHQL_QUERY_REQUEST_PATH  = "graphql/queries/requests/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/queries/responses/%s.json";

    @Autowired
    private GraphQLTestTemplate testTemplate;

    private String toJsonResponse(String jsonFileLocation) throws IOException {
        return IOUtils.toString(new ClassPathResource(jsonFileLocation).getInputStream(), StandardCharsets.UTF_8);
    }

    private void verifySuccessfulGraphQLResponse(GraphQLResponse response, String expectedResponse) throws JSONException {
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedResponse, response.getRawResponse().getBody(), true);
    }

    private GraphQLResponse retrieveGraphQLResponse(String queryFileLocation) throws IOException {
        return testTemplate.postForResource(queryFileLocation);
    }

    @Test
    public void query_address_successfully() throws IOException, JSONException {
        var fileName = "address";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_addressHistory_successfully() throws IOException, JSONException {
        var fileName = "addressHistory";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_addressHistoryPagination_successfully() throws IOException, JSONException {
        var fileName = "addressHistoryPagination";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_email_successfully() throws IOException, JSONException {
        var fileName = "email";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_emailHistory_successfully() throws IOException, JSONException {
        var fileName = "emailHistory";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_emailHistoryPagination_successfully() throws IOException, JSONException {
        var fileName = "emailHistoryPagination";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_phone_successfully() throws IOException, JSONException {
        var fileName = "phone";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_phoneHistory_successfully() throws IOException, JSONException {
        var fileName = "phoneHistory";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_phoneHistoryPagination_successfully() throws IOException, JSONException {
        var fileName = "phoneHistoryPagination";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_invalidNintendoIdRegex_successful200_withNoData_andErrorMessage() throws IOException, JSONException {
        var fileName = "invalidNintendoId";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }
}
