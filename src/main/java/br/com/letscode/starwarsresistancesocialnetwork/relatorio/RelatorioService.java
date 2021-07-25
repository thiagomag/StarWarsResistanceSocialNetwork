package br.com.letscode.starwarsresistancesocialnetwork.relatorio;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeRepository;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    final RebeldeService rebeldeService;


    public String traidoresReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
        var qtdRebeldes = listaRebeldes.size();
        List<Rebelde> listaTraidores = listaRebeldes.stream()
                .filter(rebelde -> rebelde.isTraitor()).collect(Collectors.toList());
        var qtdTraidores = listaTraidores.size();
        var traidoresPercent = (qtdTraidores*100)/(qtdRebeldes);
        return String.format("Porcentagem de traidores: %d", traidoresPercent);
    }

    public String rebeldesReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
        var qtdRebeldes = listaRebeldes.size();
        List<Rebelde> listaTraidores = listaRebeldes.stream()
                .filter(rebelde -> rebelde.isTraitor()).collect(Collectors.toList());
        var qtdTraidores = listaTraidores.size();
        var rebeldePercent = ((qtdRebeldes - qtdTraidores)*100) / (qtdRebeldes);
        return String.format("Porcentagem de rebeldes: %d", rebeldePercent);
    }

    public String pontosReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
//        List<Rebelde> listaTraidores = listaRebeldes.stream()
//                .filter(rebelde -> rebelde.getQtdReport() >= 3).collect(Collectors.toList());
//        listaTraidores.stream().map(traidor -> traidor.getInventario().size();
        //TODO pontos perdidos devido traidores
        return null;
    }

    public String recursosReport(){
        //TODO qtd media de recursos por item (calculo feito sem a contagem dos itens de traidores)
        return null;
    }
}
