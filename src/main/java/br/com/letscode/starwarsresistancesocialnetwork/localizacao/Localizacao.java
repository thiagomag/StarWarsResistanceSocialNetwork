package br.com.letscode.starwarsresistancesocialnetwork.localizacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {

    private Long x; //X measured the system's "east-west" location
    private Long y; //Y measured its "north-south" location
    private Long z; //Z measured its distance above or below the galactic plane
    private String nomeBase;

    @Override
    public String toString() {
        return x + "," +
                y + "," +
                z + "," +
                nomeBase;
    }
}
