package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.inventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.IdRebeldeInvalidoException;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NegociacaoService {

    private final RebeldeService rebeldeService;

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
        return atualizaInventario(negociacao);
    }

    private boolean inventarioTemOsItems(String id, Inventario inventario){
        if (rebeldeService.returnRebel(id).getInventario().getArma() < inventario.getArma()
                && rebeldeService.returnRebel(id).getInventario().getMunicao() < inventario.getMunicao()
                && rebeldeService.returnRebel(id).getInventario().getAgua() < inventario.getAgua())
            return false;
        return rebeldeService.returnRebel(id).getInventario().getComida() >= inventario.getComida();
    }

    @SneakyThrows
    public String atualizaInventario(Negociacao negociacao) {
        List<Rebelde> rebeldesList = rebeldeService.listAll();
        for (Rebelde rebelde : rebeldesList) {
            if (rebelde.getId().equals(negociacao.getIdRebelde1())){
                atualizaInventario(rebelde, negociacao.getInventario2(), negociacao.getInventario1());
            } else if (rebelde.getId().equals(negociacao.getIdRebelde2())){
                atualizaInventario(rebelde, negociacao.getInventario1(), negociacao.getInventario2());
            }
        }
        rebeldeService.updateRepository(rebeldesList);
        return "Negociacao feita.";
    }

    private void atualizaInventario(Rebelde rebelde, Inventario inventario1, Inventario inventario2) {
        rebelde.getInventario().arma(inventario1.getArma(),inventario2.getArma());
        rebelde.getInventario().municao(inventario1.getMunicao(),inventario2.getMunicao());
        rebelde.getInventario().agua(inventario1.getAgua(),inventario2.getAgua());
        rebelde.getInventario().comida(inventario1.getComida(),inventario2.getComida());
    }
}
