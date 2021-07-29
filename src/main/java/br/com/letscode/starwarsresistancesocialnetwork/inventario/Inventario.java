package br.com.letscode.starwarsresistancesocialnetwork.inventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    private int arma;
    private int municao;
    private int agua;
    private int comida;

    public void arma(int arma1, int arma2){
        this.arma += arma1-arma2;
    }

    public void municao(int municao1, int municao2){
        this.municao += municao1-municao2;
    }

    public void agua(int agua1, int agua2) {
        this.agua += agua1-agua2;
    }

    public void comida(int comida1, int comida2) {
        this.comida += comida1-comida2;
    }

    @Override
    public String toString() {
        return arma + "," +
                municao + "," +
                agua + "," +
                comida;
    }
}
