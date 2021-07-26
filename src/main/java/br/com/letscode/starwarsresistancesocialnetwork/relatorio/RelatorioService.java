package br.com.letscode.starwarsresistancesocialnetwork.relatorio;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.iventario.TipoItem;
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
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
        List<Rebelde> listaTraidores = listaRebeldes.stream()
                .filter(Rebelde::isTraitor).collect(Collectors.toList());
        int pontosPerdidos = 0;
        for (Rebelde rebelde : listaTraidores) {
            pontosPerdidos += valorInventario(rebelde.getInventario());
        }
        return "Foram perdidos "+pontosPerdidos+" pontos devido a traidores";
    }

    public String recursosReport() throws IOException {
        List<Rebelde> listaRebeldes = rebeldeService.listAll();
        float mediaArma = 0, mediaMunicao = 0, mediaAgua = 0, mediaComida = 0;
        for (Rebelde rebelde : listaRebeldes) {
            for (Inventario inventario : rebelde.getInventario()) {
                if (inventario.getTipoItem().equals(TipoItem.ARMA)){mediaArma += inventario.getQtd();}
                if (inventario.getTipoItem().equals(TipoItem.MUNICAO)){mediaMunicao += inventario.getQtd();}
                if (inventario.getTipoItem().equals(TipoItem.AGUA)){mediaAgua+= inventario.getQtd();}
                if (inventario.getTipoItem().equals(TipoItem.COMIDA)){mediaComida += inventario.getQtd();}
            }
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

    public int valorInventario(List<Inventario> listInventario) {
        int valor = 0;
        for (Inventario inventario : listInventario) {
            if (inventario.getTipoItem().equals(TipoItem.ARMA)){valor += inventario.getQtd()*4;}
            if (inventario.getTipoItem().equals(TipoItem.MUNICAO)){valor += inventario.getQtd()*3;}
            if (inventario.getTipoItem().equals(TipoItem.AGUA)){valor += inventario.getQtd()*2;}
            if (inventario.getTipoItem().equals(TipoItem.COMIDA)){valor += inventario.getQtd();}
        }
        return valor;
    }
}
