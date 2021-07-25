package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneroRebelde {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String description;
}