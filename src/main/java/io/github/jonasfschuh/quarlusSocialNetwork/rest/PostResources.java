package io.github.jonasfschuh.quarlusSocialNetwork.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResources {

    @POST
    public Response savePost() {
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listAllPosts() {
        return Response.ok().build();
    }

}
