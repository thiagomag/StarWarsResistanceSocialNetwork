package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotBlank;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RebeldeETraidorException extends RuntimeException {
    public RebeldeETraidorException(@NotBlank(message = "ID é obrigatório") String idRebelde) {
        super("O rebelde "+idRebelde+" é um traidor e não pode fazer negociações");
    }
}
