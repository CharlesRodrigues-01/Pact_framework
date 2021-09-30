package br.com.zup.charles.pact_consumer.response;

public class CreateResponse {

    private final Long id;
    private final String message;

    public CreateResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
