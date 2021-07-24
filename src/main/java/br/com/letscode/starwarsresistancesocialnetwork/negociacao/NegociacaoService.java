package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Iventario;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NegociacaoService {

    final NegociacaoRepository negociacaoRepository;
    final RebeldeService rebeldeService;

    public String checkTrade(String nome, Iventario iventario) throws IOException {
        //TODO implementar a verificação nome existente para ser possivel criar negociacao
        if(rebeldeService.checkRebel(nome)) {
            if(checkListNull(nome, iventario) == null) {
                if (compareTrade(nome, iventario)) {
                    //TODO implementar atualização dos inventarios
                    negociacaoRepository.clearNegociacao(); //Limpa o arquivo de negociacao após o termino
                    return "Negociação realizada! Iventários atualizados com os novos itens.";
                }
            }
            return nome + ", a negociação não é equivalente!";
        }
        return nome + " não existe!";
    }

    public String checkListNull(String nome, Iventario iventario) throws IOException {
        if (negociacaoRepository.listAll().size() == 0) { //Se a lista de negociacao for vazia, cria uma nova negociação
            createTrade(nome, iventario);
            return nome + ", negociação solicitada!";
        }
        return null;
    }

    public void createTrade(String nome, Iventario iventario) throws IOException {
        negociacaoRepository.inserirArquivo(nome, iventario);
    }

    public boolean compareTrade(String nome, Iventario iventario) throws IOException {
        var listNegociacao = negociacaoRepository.listAll();
        //TODO implementar verificação de pontos
        //TODO realizar a comparação, caso os pontos sejam equivalentes: realiza a troca, caso negativo: não realiza
        return false;
    }

}
