package br.com.zup.charles.providerPact.response;

import br.com.zup.charles.providerPact.model.User;

public class CreateResponse {

    private final Long id;
    private String message;

    public CreateResponse(User user) {
        this.id = user.getId();
        this.setMessage();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(){
        this.message = "Cadastro realizado com sucesso!";
    }
}
