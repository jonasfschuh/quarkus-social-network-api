package io.github.jonasfschuh.quarlusSocialNetwork.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Age cannot be null")
    private Integer age;

}
