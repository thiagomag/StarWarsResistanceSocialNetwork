package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NegociacaoRepository {

    private final RebeldeRepository rebeldeRepository;

    @SneakyThrows
    public String troca(Negociacao negociacao) {
        List<Rebelde> rebeldesList = rebeldeRepository.listAll();
        for (Rebelde rebelde : rebeldesList) {
            if (rebelde.getId().equals(negociacao.getIdRebelde1())){
                troca(rebelde, negociacao.getInventario2(), negociacao.getInventario1());
            }
            if (rebelde.getId().equals(negociacao.getIdRebelde2())){
                troca(rebelde, negociacao.getInventario1(), negociacao.getInventario2());
            }
        }
        rebeldeRepository.reescreverArquivo(rebeldesList);
        return "Negociacao feita.";
    }

    private void troca(Rebelde rebelde, Inventario inventario1,Inventario inventario2) {
            rebelde.getInventario().arma(inventario1.getArma(),inventario2.getArma());
            rebelde.getInventario().municao(inventario1.getMunicao(),inventario2.getMunicao());
            rebelde.getInventario().agua(inventario1.getAgua(),inventario2.getAgua());
            rebelde.getInventario().comida(inventario1.getComida(),inventario2.getComida());
    }
}
