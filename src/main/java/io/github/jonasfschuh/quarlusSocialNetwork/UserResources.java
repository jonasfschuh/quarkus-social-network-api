package io.github.jonasfschuh.quarlusSocialNetwork;

import io.github.jonasfschuh.quarlusSocialNetwork.domain.repository.UserRepository;
import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.CreateUserRequest;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.User;
import io.github.jonasfschuh.quarlusSocialNetwork.rest.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    private final UserRepository repository;
    private final Validator validator;

    @Inject
    public UserResources(UserRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(400).entity(responseError).build();
        }

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        repository.persist(user);
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers() {
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        User user = repository.findById(id);

        if (user != null) {
            repository.delete(user);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userRequest) {
        User user = repository.findById(id);

        if (user != null) {
            user.setName(userRequest.getName());
            user.setAge(userRequest.getAge());
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
