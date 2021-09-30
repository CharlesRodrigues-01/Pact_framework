package br.com.zup.charles.provider_pact.request;

import br.com.zup.charles.provider_pact.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRequestDto {

    @NotBlank
    private final String name;
    @NotBlank
    @Email
    private final String email;

    public UserRequestDto(@NotBlank String name, @Email String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User toModel() {
        return new User(this.name, this.email);
    }
}
