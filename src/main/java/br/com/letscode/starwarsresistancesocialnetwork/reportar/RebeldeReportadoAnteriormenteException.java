package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class RebeldeReportadoAnteriormenteException extends RuntimeException {
    public RebeldeReportadoAnteriormenteException(String idTraidor) {
        super("Você já reportou o rebelde com id " + idTraidor + " anteriormente");
    }
}
