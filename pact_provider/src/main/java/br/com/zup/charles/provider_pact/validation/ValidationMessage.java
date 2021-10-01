package br.com.zup.charles.provider_pact.validation;

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
