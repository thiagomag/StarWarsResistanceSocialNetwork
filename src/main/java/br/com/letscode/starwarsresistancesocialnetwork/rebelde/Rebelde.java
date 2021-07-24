package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rebelde {

    private String nome;
    private Integer idade;
    private String genero;
    private Localizacao localizacao;
    private List<Inventario> inventario;
    @JsonIgnore
    private Integer qtdReport = 0;
    @JsonIgnore
    private boolean isTraitor;
}