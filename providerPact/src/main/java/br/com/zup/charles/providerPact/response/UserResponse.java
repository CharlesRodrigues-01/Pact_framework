package br.com.zup.charles.providerPact.response;

import br.com.zup.charles.providerPact.model.User;

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
}
