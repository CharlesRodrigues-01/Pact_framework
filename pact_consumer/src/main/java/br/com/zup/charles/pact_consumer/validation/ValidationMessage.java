package br.com.zup.charles.pact_consumer.validation;

public class ValidationMessage {

    private final String field;
    private final String message;

    public ValidationMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
