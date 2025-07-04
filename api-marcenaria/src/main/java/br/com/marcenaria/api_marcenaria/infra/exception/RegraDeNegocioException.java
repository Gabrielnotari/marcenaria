package br.com.marcenaria.api_marcenaria.infra.exception;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException(String message) {
        super(message);
    }
}
