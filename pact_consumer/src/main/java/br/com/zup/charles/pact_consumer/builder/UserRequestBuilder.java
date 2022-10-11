package br.com.zup.charles.pact_consumer.builder;

import br.com.zup.charles.pact_consumer.request.UserRequestDto;

public class UserRequestBuilder {
    private String name;
    private String email;

    public UserRequestBuilder name(String name){
        this.name = name;
        return this;
    }

    public UserRequestBuilder email(String email){
        this.email = email;
        return this;
    }

    public UserRequestDto build(){
        return new UserRequestDto(name, email);
    }
}
