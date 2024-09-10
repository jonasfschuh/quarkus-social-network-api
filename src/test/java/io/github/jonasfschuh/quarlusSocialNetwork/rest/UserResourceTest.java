package io.github.jonasfschuh.quarlusSocialNetwork.rest;

import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.CreateUserRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserResourceTest {

    @Test
    @DisplayName("Should create an user successfully")
    public void CreateUserTest() {
        var user = new CreateUserRequest();
        user.setName("User Test");
        user.setAge(30);

        var response =
            given()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/users")
            .then()
                .extract().response();

        assertEquals(201, response.statusCode());
        assertNotNull(response.jsonPath().getString("id"));
    }

}