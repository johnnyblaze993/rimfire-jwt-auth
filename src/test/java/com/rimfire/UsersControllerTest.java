package com.rimfire;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@MicronautTest
public class UsersControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 2222;  // Your application's port
    }

    @Test
    public void testGetAllUsers() {
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .body("size()", is(1));
    }

    @Test
    public void testFindByUsername() {
        given()
            .pathParam("username", "USER")
            .when()
            .get("/users/{username}")
            .then()
            .statusCode(200)
            .body("username", equalTo("USER"));
    }
}
