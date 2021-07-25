package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NegociacaoService {

    final NegociacaoRepository negociacaoRepository;

    final RebeldeService rebeldeService;

    public String checkTrade(String id, Inventario inventario) throws IOException {
        if (rebeldeService.checkRebel(id)) {
            if (CheckListNotNull(id, inventario)) {
                if (compareTrade(id, inventario)) {
                    //TODO implementar atualização dos inventarios
                    negociacaoRepository.clearNegociacao(); //Limpa o arquivo de negociacao após o termino
                    return "Negociação realizada! Iventários atualizados com os novos itens.";
                }
                return "A negociação não é equivalente!";
            }
            return "Nova negociação solicitada!";
        }
        return id+" não existe!";
    }

    public boolean CheckListNotNull(String id, Inventario inventario) throws IOException {
        if (negociacaoRepository.listAll().size() == 0) { //Se a lista de negociacao for vazia, cria uma nova negociação
            createTrade(id, inventario);
            return false;
        }
        return true;
    }

    public void createTrade(String id, Inventario inventario) throws IOException {
        negociacaoRepository.inserirArquivo(id, inventario);
    }

    public boolean compareTrade(String nome, Inventario inventario) throws IOException {
        var listNegociacao = negociacaoRepository.listAll();
        //TODO implementar verificação de pontos
        //TODO realizar a comparação, caso os pontos sejam equivalentes: realiza a troca, caso negativo: não realiza
        return false;
    }

}
