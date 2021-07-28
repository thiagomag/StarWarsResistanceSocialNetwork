package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NegociacaoService {

    final NegociacaoRepository negociacaoRepository;

    final RebeldeService rebeldeService;

    @SneakyThrows
    public String negociacao(Negociacao negociacao) {
        if (rebeldeService.checkRebel(negociacao.getIdRebelde1())
        && rebeldeService.checkRebel(negociacao.getIdRebelde2())){
            return checarValor(negociacao);
        }
        return "invalid id";
    }

    private String checarValor(Negociacao negociacao) {
        if (rebeldeService.valorInventario(negociacao.getInventario1())
        == rebeldeService.valorInventario(negociacao.getInventario2())) {
            if (inventarioTemOsItems(negociacao.getIdRebelde1(),negociacao.getInventario1())
            && inventarioTemOsItems(negociacao.getIdRebelde2(),negociacao.getInventario2())){
                return negociacaoRepository.troca(negociacao);
            }
        }
        return "inventarios invalidos";
    }

    private boolean inventarioTemOsItems(String id, Inventario inventario){
        if (rebeldeService.returnRebel(id).getInventario().getArma() <
        inventario.getArma()){return false;}
        if (rebeldeService.returnRebel(id).getInventario().getMunicao() <
                inventario.getMunicao()){return false;}
        if (rebeldeService.returnRebel(id).getInventario().getAgua() <
                inventario.getAgua()){return false;}
        return rebeldeService.returnRebel(id).getInventario().getComida() >= inventario.getComida();
    }
}
