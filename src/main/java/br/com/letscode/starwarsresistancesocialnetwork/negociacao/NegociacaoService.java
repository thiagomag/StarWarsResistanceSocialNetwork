package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.inventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.IdRebeldeInvalidoException;
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
        if (rebeldeService.checkRebel(negociacao.getIdRebelde1())){
            throw new IdRebeldeInvalidoException(negociacao.getIdRebelde1());
        }
        if (rebeldeService.checkRebel(negociacao.getIdRebelde2())){
            throw new IdRebeldeInvalidoException(negociacao.getIdRebelde2());
        }
        return checarInventario(negociacao);
    }

    private String checarInventario(Negociacao negociacao) {
        if (rebeldeService.valorInventario(negociacao.getInventario1())
                != rebeldeService.valorInventario(negociacao.getInventario2())) {
            throw new ValoresNaoBatemException();
        }
        if (!(inventarioTemOsItems(negociacao.getIdRebelde1(),negociacao.getInventario1())
                && inventarioTemOsItems(negociacao.getIdRebelde2(),negociacao.getInventario2()))){
            throw new InventarioNaoTemOsItensException();
        }
        return negociacaoRepository.atualizaInventario(negociacao);
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
