package br.com.zup.charles.provider_pact.response;

import br.com.zup.charles.provider_pact.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    private final Long id;
    private final String name;
    private final String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static List<UserResponse> convert(List<User> users) {
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
