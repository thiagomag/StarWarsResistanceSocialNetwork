package br.com.letscode.starwarsresistancesocialnetwork.relatorio;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    final RebeldeService rebeldeService;

    public String traidoresReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
        var qtdRebeldes = listaRebeldes.size();
//        var qtdTraidores = listaTraidores.size();
        //TODO listaTraidores.size();
        //TODO var traidoresPercent = (qtdTraidores*100) / (qtdRebeldes+qtdTraidores)
//        return String.format("Porcentagem de traidores: %d%", traidoresPercent);
        return null;
    }

    public String rebeldesReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
        var qtdRebeldes = listaRebeldes.size();
//        var qtdTraidores = listaTraidores.size();
        //TODO listaTraidores.size();
        //TODO var rebeldePercent = (qtdRebeldes*100) / (qtdRebeldes+qtdTraidores)
//        return String.format("Porcentagem de rebeldes: %d%", rebeldePercent);
        return null;
    }

    public String pontosReport(){
        //TODO pontos perdidos devido traidores
        return null;
    }

    public String recursosReport(){
        //TODO qtd media de recursos por item (calculo feito sem a contagem dos itens de triadores)
        return null;
    }
}
