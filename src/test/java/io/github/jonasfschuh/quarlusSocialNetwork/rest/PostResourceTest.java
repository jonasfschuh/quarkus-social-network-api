package io.github.jonasfschuh.quarlusSocialNetwork.rest;

import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.User;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.repository.UserRepository;
import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.CreatePostRequest;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(PostResource.class)
class PostResourceTest {

    @Inject
    UserRepository userRepository;
    Long userId;

    @BeforeEach
    @Transactional
    public void setup() {
        var user = new User();
        user.setName("User Test");
        user.setAge(30);
        userRepository.persist(user);
        userId = user.getId();
    }

    @Test
    @DisplayName("Should create a post successfully")
    public void createPostTest() {
        var postRequest = new CreatePostRequest();
        postRequest.setText("Post Test");

       given()
            .contentType(ContentType.JSON)
            .body(postRequest)
            .pathParam("userId", userId)
        .when()
            .post()
        .then()
            .statusCode(201);
    }

    @Test
    @DisplayName("Should return 404 when trying to make a post to a non-existent user")
    public void inexistentUserTest() {
        var postRequest = new CreatePostRequest();
        postRequest.setText("Post Test");

        var inexistentUserId = 999L;

        given()
            .contentType(ContentType.JSON)
            .body(postRequest)
            .pathParam("userId", inexistentUserId)
        .when()
            .post()
        .then()
            .statusCode(404);
    }

    @Test
    @DisplayName("Should return 404 when user doesn´t exist")
    public void listPostUserNotFoundTest() {
        var inexistentUserId = 999L;
        given()
            .pathParam("userId", inexistentUserId)
            .header("followerId", userId)
        .when()
            .get()
        .then()
            .statusCode(404);
    }

    @Test
    @DisplayName("Should return 400 when followerId header is not present ")
    public void listPostFollowerHeaderNotSendTest() {
        given()
            .pathParam("userId", userId)
        .when()
            .get()
        .then()
            .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when follower doesn´t exist ")
    public void listPostFollowerNotFoundTest() {
        var inexistentFollowerId = 999L;
        given()
            .pathParam("userId", userId)
            .header("followerId", inexistentFollowerId)
        .when()
            .get()
        .then()
            .statusCode(400);
    }

    @Test
    @DisplayName("Should return 403 when follower isn´t a follower ")
    public void listPostFollowerNotAFollowerTest() {
        var followerId = userId;
        given()
            .pathParam("userId", userId)
            .header("followerId", followerId)
        .when()
            .get()
        .then()
            .statusCode(403);
    }

    @Test
    @DisplayName("Should return posts")
    public void listPostTest() {
        var follower = new User();
        follower.setName("Follower Test");
        follower.setAge(30);
        userRepository.persist(follower);


    }


}