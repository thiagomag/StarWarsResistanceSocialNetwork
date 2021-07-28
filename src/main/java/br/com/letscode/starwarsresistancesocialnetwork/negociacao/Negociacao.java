package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.TipoItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Negociacao {
    @NotNull
    private String id;
    @NotNull
    private TipoItem tipoItem;
    @NotEmpty
    private int qtd;

    public Negociacao(String txtLinhas) {
        String[] split = txtLinhas.split(",");
        this.id = split[0];
        this.tipoItem = TipoItem.valueOf(split[1]);
        this.qtd = Integer.parseInt(split[2]);
    }
}
