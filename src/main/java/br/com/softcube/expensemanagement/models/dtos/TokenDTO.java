package br.com.softcube.expensemanagement.models.dtos;

public class TokenDTO {

    private final String type;
    private final String token;

    public TokenDTO(String type, String token) {
        this.type = type;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }
}
