# Fun with GraphQL
This is just a simple Spring GraphQL API that is part of a schema stitching exercise over on [Fun with GraphQL Schema Stitching](https://github.com/HeroOfTheWild/Fun-with-GraphQL-Schema-Stitching).

## Next Steps
- Mutations will be added next and we'll see how that comes into play with [Fun with GraphQL Schema Stitching](https://github.com/HeroOfTheWild/Fun-with-GraphQL-Schema-Stitching).

## Interesting things to learn from this Project
- Defining Schema Types in `schema.graphql`
- Custom GraphQL Scalar Types for validation
- QueryResolvers and how they work with our GraphQL Queries 
- Async Threads for executing GraphQL Queries
- Pagination with Relay Connection
- Integration Test with Spring GraphQL
- Working with a local H2 Database

## Running this application.
`./gradlew clean build`
`./gradlew bootRun`

Application will be exposed on port 8082. You can send your POST request against http://localhost:8082/nintendo/contact/graphql.

## Playing with this API 
Checkout the attached Postman Collection for more examples. Below is one for querying all contact details

```graphql
query contactInformation($nintendoId: NintendoId!){
    addresses(id: $nintendoId) {
        id
        nintendoId
        purpose
        country
        stateProvince
        cityName
        streetAddress
        regionCode
        postalCode
        startDate
        endDate
        lastModified
    }
    phones(id: $nintendoId) {
        id
        nintendoId
        type
        purpose
        countryCode
        number
        lastModified
    }
    emails(id: $nintendoId) {
        id
        nintendoId
        purpose
        emailAddress
        lastModified
    }
}
```

```json 
{
    "nintendoId": "nin0001"
}
```