package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InventarioNaoTemOsItensException extends RuntimeException {
    public InventarioNaoTemOsItensException() {
        super("Inventario não tem os itens!");
    }
}
