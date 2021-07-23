package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Iventario;
import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
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
    private int idade;
    private String genero;
    private Localizacao localizacao;
    private List<Iventario> iventario;
}