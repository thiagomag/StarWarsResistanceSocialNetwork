package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class RebeldeJaEhTraidor extends RuntimeException {
    public RebeldeJaEhTraidor(String idTraidor) {
        super("O rebelde com o id " + idTraidor + " já está registrado como traidor.");
    }
}
