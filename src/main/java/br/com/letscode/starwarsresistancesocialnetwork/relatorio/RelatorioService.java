package br.com.letscode.starwarsresistancesocialnetwork.relatorio;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
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
                .filter(Rebelde::isTraitor).collect(Collectors.toList());
        var qtdTraidores = (double) listaTraidores.size();
        var traidoresPercent = (qtdTraidores*100)/(qtdRebeldes);
        return String.format("Porcentagem de traidores: %.2f%%", traidoresPercent);
    }

    public String rebeldesReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
        var qtdRebeldes = listaRebeldes.size();
        List<Rebelde> listaTraidores = listaRebeldes.stream()
                .filter(Rebelde::isTraitor).collect(Collectors.toList());
        var qtdTraidores = (double) listaTraidores.size();
        var rebeldePercent = ((qtdRebeldes - qtdTraidores)*100) / (qtdRebeldes);
        return String.format("Porcentagem de rebeldes: %.2f%%", rebeldePercent);
    }

    public String pontosReport() throws IOException {
        List<Rebelde> listaTraidores = rebeldeService.listAll().stream()
                .filter(Rebelde::isTraitor).collect(Collectors.toList());
        int pontosPerdidos = 0;
        for (Rebelde rebelde : listaTraidores) {
            pontosPerdidos += rebeldeService.valorInventario(rebelde.getInventario());
        }
        return "Foram perdidos "+pontosPerdidos+" pontos devido a traidores";
    }

    public String recursosReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll().stream()
                .filter(Rebelde -> !Rebelde.isTraitor()).collect(Collectors.toList());
        float mediaArma = 0, mediaMunicao = 0, mediaAgua = 0, mediaComida = 0;
        for (Rebelde rebelde : listaRebeldes) {
            mediaArma += rebelde.getInventario().getArma();
            mediaMunicao += rebelde.getInventario().getMunicao();
            mediaAgua += rebelde.getInventario().getAgua();
            mediaComida += rebelde.getInventario().getComida();
        }
        var listSize = listaRebeldes.size();
        mediaArma /= listSize;
        mediaMunicao /= listSize;
        mediaAgua /= listSize;
        mediaComida /= listSize;
        return String.format("Relatório de items:\n%.2f armas por rebelde,\n" +
                "%.2f munições por rebelde,\n%.2f aguas por rebelde,\n%.2f comidas por rebelde."
                ,mediaArma,mediaMunicao,mediaAgua,mediaComida);
    }

}
