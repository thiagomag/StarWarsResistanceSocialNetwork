package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reportar {

    private String idDenunciante;
    private String idTraidor;

}
