package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.inventario.Inventario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Negociacao {

    @NotBlank(message = "ID é obrigatório")
    private String idRebelde1;
    @NotNull(message = "Inventário é obrigatório")
    private Inventario inventario1;
    @NotBlank(message = "ID é obrigatório")
    private String idRebelde2;
    @NotNull(message = "Inventário é obrigatório")
    private Inventario inventario2;
}
