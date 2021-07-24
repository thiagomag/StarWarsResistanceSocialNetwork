package br.com.letscode.starwarsresistancesocialnetwork.iventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    private TipoItem tipoItem;
    private int qtd;

    @Override
    public String toString() {
        return tipoItem + "," + qtd;
    }
}
