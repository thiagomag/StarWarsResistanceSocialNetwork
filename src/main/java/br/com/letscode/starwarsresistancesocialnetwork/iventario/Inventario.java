package br.com.letscode.starwarsresistancesocialnetwork.iventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @NotNull
    private TipoItem tipoItem;
    @NotEmpty
    private int qtd;

    @Override
    public String toString() {
        return tipoItem + "," + qtd;
    }
}
