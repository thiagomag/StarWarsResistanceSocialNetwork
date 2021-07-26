package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdRebeldeInvalidoException extends RuntimeException {
    public IdRebeldeInvalidoException(String id) {
        super("O id " + id + " informado não consta no nosso banco de dados");
    }
}