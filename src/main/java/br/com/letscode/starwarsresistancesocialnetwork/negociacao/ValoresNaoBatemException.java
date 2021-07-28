package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValoresNaoBatemException extends RuntimeException{
    public ValoresNaoBatemException() {
        super("Os valores dos itens não são compativeis");
    }
}
