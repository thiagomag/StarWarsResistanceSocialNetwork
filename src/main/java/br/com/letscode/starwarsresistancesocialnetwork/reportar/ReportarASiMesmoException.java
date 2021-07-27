package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReportarASiMesmoException extends RuntimeException {
    public ReportarASiMesmoException() {
        super("Você não pode reportar a si mesmo.");
    }
}
