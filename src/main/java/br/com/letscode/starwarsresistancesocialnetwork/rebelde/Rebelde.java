package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.inventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rebelde {

    private String id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Idade é obrigatório")
    private Integer idade;
    @NotNull(message = "Genero é obrigatório")
    private GeneroRebelde genero;
    @NotNull(message = "Localização é obrigatório")
    private Localizacao localizacao;
    @NotNull(message = "Itens no inventário são obrigatórios")
    private Inventario inventario;
    @JsonIgnore
    private Integer qtdReport = 0;
    @JsonIgnore
    private boolean isTraitor;
}