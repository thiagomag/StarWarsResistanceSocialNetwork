package br.com.letscode.starwarsresistancesocialnetwork.negociacao;
import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.iventario.TipoItem;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.IdRebeldeInvalidoException;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NegociacaoService {

    private final NegociacaoRepository negociacaoRepository;

    private final RebeldeService rebeldeService;

    public String checkTrade(String id, List<Inventario> inventario) throws IOException {
        if (rebeldeService.checkRebel(id)) {
                if (compareTrade(id, inventario)) {
                    return "Negociação realizada! Inventários atualizados com os novos itens.";
                }
                createTrade(id, inventario);
                return "Nova negociação solicitada!";
            }
        throw new IdRebeldeInvalidoException(id);
    }

    public void createTrade(String id, List<Inventario> inventario) throws IOException {
        negociacaoRepository.inserirArquivo(id, inventario);
    }

    public boolean compareTrade(String id, List<Inventario> inventario) throws IOException {
        List <Negociacao> newTrade = new ArrayList<>();
        for (Inventario value : inventario) {
            newTrade.add(new Negociacao(id, value.getTipoItem(), value.getQtd()));
        }
        var pontosNewTrade = calcScoreTrade(newTrade);
        var oldTrade = negociacaoRepository.getAll();
        var pontosOldTrade = calcScoreTrade(oldTrade);

        if(pontosNewTrade.equals(pontosOldTrade)){
            rebeldeService.receiveTrade(oldTrade, newTrade);
            return true;
        } else {
            return false;
        }
    }

    public Integer calcScoreTrade(List<Negociacao> listGetPontos){
        var totalPontos = 0;
        for (Negociacao listCalcPonto : listGetPontos) {
            if (listCalcPonto.getTipoItem().equals((TipoItem.ARMA))) {
                totalPontos += listCalcPonto.getQtd() * 4;
            }
            if (listCalcPonto.getTipoItem().equals((TipoItem.MUNICAO))) {
                totalPontos += listCalcPonto.getQtd() * 3;
            }
            if (listCalcPonto.getTipoItem().equals((TipoItem.AGUA))) {
                totalPontos += listCalcPonto.getQtd() * 2;
            }
            if (listCalcPonto.getTipoItem().equals((TipoItem.COMIDA))) {
                totalPontos += listCalcPonto.getQtd();
            }
        }
        return totalPontos;
    }
//    public boolean compareTrade(String id, List<Inventario> inventario) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Oque deseja comprar? \n 1 - Comida \n 2 - Água \n 3 - Munição \n 4 - Arma");
//        var opc = sc.nextInt();
//        System.out.print("Informe a quantidade:");
//        var qtd = sc.nextInt();
//        sc.close();
//        var vendedor = rebeldeService.listAll().stream().filter(rebelde -> rebelde.getId().equals(id));
//        var scoreVendedor = calcScore(vendedor.findFirst().get().getInventario());
//
//        if(scoreVendedor >= (opc*qtd)){
//            return true;
//        }
//        return false;
//    }
//
//    public Integer calcScore(List<Inventario> inventario){
//        var totalPonto = 0;
//
//            for (Inventario invent : inventario) {
//                if (invent.getTipoItem().equals(TipoItem.ARMA)){totalPonto += invent.getQtd()*4;}
//                if (invent.getTipoItem().equals(TipoItem.MUNICAO)){totalPonto += invent.getQtd()*3;}
//                if (invent.getTipoItem().equals(TipoItem.AGUA)){totalPonto += invent.getQtd()*2;}
//                if (invent.getTipoItem().equals(TipoItem.COMIDA)){totalPonto += invent.getQtd();}
//            }
//        return totalPonto;
//    }

}