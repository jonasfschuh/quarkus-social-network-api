package io.github.jonasfschuh.quarlusSocialNetwork.rest;

import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.Post;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.User;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.repository.PostRepository;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.repository.UserRepository;
import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.CreatePostRequest;
import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResources {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Inject
    public PostResources(UserRepository userRepository,
                         PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest postRequest) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(postRequest.getText());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());
        postRepository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listAllPosts(@PathParam("userId") Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PanacheQuery<Post> allPosts = postRepository.find(
                "user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = allPosts.list();

        var postResponseList = list.stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.status(Response.Status.FOUND).entity(postResponseList).build();
    }

}
