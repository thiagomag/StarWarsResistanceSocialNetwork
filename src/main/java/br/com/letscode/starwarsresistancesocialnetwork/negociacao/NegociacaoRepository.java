package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeRepository;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NegociacaoRepository {

    private final RebeldeRepository rebeldeRepository;
    private final RebeldeService rebeldeService;

    @SneakyThrows
    public String troca(Negociacao negociacao) {
        List<Rebelde> rebeldesList = rebeldeRepository.listAll();
        for (Rebelde rebelde : rebeldesList) {
            if (rebelde.getId().equals(negociacao.getIdRebelde1())){
                rebelde.getInventario().setArma(
                        rebelde.getInventario().getArma()+negociacao.getInventario2().getArma()
                                -negociacao.getInventario1().getArma());
                rebelde.getInventario().setMunicao(
                        rebelde.getInventario().getMunicao()+negociacao.getInventario2().getMunicao()
                                -negociacao.getInventario1().getMunicao());
                rebelde.getInventario().setAgua(
                        rebelde.getInventario().getAgua()+negociacao.getInventario2().getAgua()
                                -negociacao.getInventario1().getAgua());
                rebelde.getInventario().setComida(
                        rebelde.getInventario().getComida()+negociacao.getInventario2().getComida()
                                -negociacao.getInventario1().getComida());
            }
            if (rebelde.getId().equals(negociacao.getIdRebelde2())){
                rebelde.getInventario().setArma(
                        rebelde.getInventario().getArma()+negociacao.getInventario1().getArma()
                                -negociacao.getInventario2().getArma());
                rebelde.getInventario().setMunicao(
                        rebelde.getInventario().getMunicao()+negociacao.getInventario1().getMunicao()
                                -negociacao.getInventario2().getMunicao());
                rebelde.getInventario().setAgua(
                        rebelde.getInventario().getAgua()+negociacao.getInventario1().getAgua()
                                -negociacao.getInventario2().getAgua());
                rebelde.getInventario().setComida(
                        rebelde.getInventario().getComida()+negociacao.getInventario1().getComida()
                                -negociacao.getInventario2().getComida());
            }
        }
        rebeldeRepository.reescreverArquivo(rebeldesList);
        return "Negociacao feita.";
    }
}
