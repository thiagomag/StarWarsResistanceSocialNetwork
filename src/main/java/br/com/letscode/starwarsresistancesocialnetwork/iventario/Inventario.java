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

    private int arma;
    private int municao;
    private int agua;
    private int comida;

    @Override
    public String toString() {
        return arma + "," +
                municao + "," +
                agua + "," +
                comida;
    }
}
