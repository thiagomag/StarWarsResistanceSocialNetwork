package br.com.letscode.starwarsresistancesocialnetwork.inventario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoItem {

    ARMA("arma"),
    MUNICAO("munição"),
    AGUA("água"),
    COMIDA("comida");

    private final String description;
}
