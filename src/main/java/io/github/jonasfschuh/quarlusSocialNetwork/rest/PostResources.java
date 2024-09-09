package io.github.jonasfschuh.quarlusSocialNetwork.rest;

import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.Post;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.User;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.repository.PostRepository;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.repository.UserRepository;
import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.CreatePostRequest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResources {

    private final UserRepository repository;
    private final PostRepository postRepository;

    @Inject
    public PostResources(UserRepository repository,
                         PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest postRequest) {
        User user = repository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(postRequest.getText());
        post.setUser(user);
        repository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listAllPosts(@PathParam("userId") Long userId) {
        User user = repository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }

}
