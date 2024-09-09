package io.github.jonasfschuh.quarlusSocialNetwork.rest;

import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.CreateUserRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    @POST
    public Response createUser(CreateUserRequest userRequest ) {
        return Response.ok(userRequest).build();
    }

    @GET
    public Response listAllUsers() {
        return Response.ok().build();
    }

}
