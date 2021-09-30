package br.com.zup.charles.pact_consumer.serviceClientPactTest;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Provider")
class ServiceClientPactTest {

    private final String consumerName = "Consumer";

    @Pact(consumer = consumerName)
    public RequestResponsePact getSingleUser(PactDslWithProvider builder) {

        PactDslJsonBody bodyResponse = new PactDslJsonBody()
                .integerType("id", 1L)
                .stringType("name", "Teste")
                .stringType("email", "teste@hotmail.com");

        return builder
                .given("user with ID 1 exists", "id", 1L)
                .uponReceiving("get user with ID 1")
                .path("/users/1")
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(200)
                .body(bodyResponse)
                .toPact();
    }

    @Test
    @DisplayName("Should get a user by ID")
    @PactTestFor(pactMethod = "getSingleUser")
    void testSingleProduct(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/users/1").execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
    }

    @Pact(consumer = consumerName)
    public RequestResponsePact singleUserDoesNotExist(PactDslWithProvider builder) {
        return builder
                .given("user with ID 10 does not exist", "id", 10L)
                .uponReceiving("get user with ID 10")
                .path("/users/10")
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Test
    @DisplayName("Should not get a user by non-existent ID")
    @PactTestFor(pactMethod = "singleUserDoesNotExist")
    void testSingleUserDoesNotExists(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/users/10").execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(404)));
    }

    @Pact(consumer = consumerName)
    public RequestResponsePact postSingleUser(PactDslWithProvider builder) {

        PactDslJsonBody bodyResponse = new PactDslJsonBody()
                .integerType("id", 1L)
                .stringType("message", "Cadastro realizado com sucesso!");

        return builder
                .given("perform a POST request to create a user")
                .uponReceiving("create a user")
                .path("/users")
                .method(HttpMethod.POST.name())
                .body("{\n" +
                        "    \"name\":\"Teste2\",\n" +
                        "    \"email\":\"teste2@hotmail.com\"\n" +
                        "}")
                .willRespondWith()
                .status(201)
                .body(bodyResponse)
                .toPact();
    }

    @Test
    @DisplayName("Should create a user")
    @PactTestFor(pactMethod = "postSingleUser")
    void testPostSingleUser(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Post(mockServer.getUrl() + "/users")
                .bodyString("{\n" +
                        "    \"name\":\"Teste2\",\n" +
                        "    \"email\":\"teste2@hotmail.com\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(201)));
    }

    @Pact(consumer = consumerName)
    public RequestResponsePact userWithEmailExistent(PactDslWithProvider builder) {

        return builder
                .given("user with existent email")
                .uponReceiving("post user with existent email")
                .path("/users")
                .method(HttpMethod.POST.name())
                .body("{\n" +
                        "    \"name\":\"Teste2\",\n" +
                        "    \"email\":\"teste2@hotmail.com\"\n" +
                        "}")
                .willRespondWith()
                .status(400)
                .toPact();
    }

    @Test
    @DisplayName("Should not post a user by existent email")
    @PactTestFor(pactMethod = "userWithEmailExistent")
    void testPostWithExistentEmail(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Post(mockServer.getUrl() + "/users")
                .bodyString("{\n" +
                        "    \"name\":\"Teste2\",\n" +
                        "    \"email\":\"teste2@hotmail.com\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(400)));
    }

}
