package com.rimfire;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@MicronautTest
public class HelloControllerTest {

    @Test
    void testHelloWorldResponse() {
        RestAssured.port = 2222; // Change this to your server's port if different

        given()
        .when().get("/hello")
        .then()
        .statusCode(200)
        .body(equalTo("Hello World"));
    }
}