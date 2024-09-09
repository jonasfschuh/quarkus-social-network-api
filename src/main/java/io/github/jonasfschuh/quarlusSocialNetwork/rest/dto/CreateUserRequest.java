package io.github.jonasfschuh.quarlusSocialNetwork.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Age cannot be null")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
