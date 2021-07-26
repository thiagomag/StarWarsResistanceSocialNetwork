package br.com.letscode.starwarsresistancesocialnetwork.localizacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {

    @NotNull
    private Long x; //X measured the system's "east-west" location
    @NotNull
    private Long y; //Y measured its "north-south" location
    @NotNull
    private Long z; //Z measured its distance above or below the galactic plane
    @NotNull
    private String nomeBase;

    @Override
    public String toString() {
        return x + "," +
                y + "," +
                z + "," +
                nomeBase;
    }
}