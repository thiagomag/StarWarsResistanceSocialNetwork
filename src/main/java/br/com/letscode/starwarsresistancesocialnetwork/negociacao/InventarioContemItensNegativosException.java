package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InventarioContemItensNegativosException extends RuntimeException {
    public InventarioContemItensNegativosException() {
        super("O inventario contem itens Negativos");
    }
}
