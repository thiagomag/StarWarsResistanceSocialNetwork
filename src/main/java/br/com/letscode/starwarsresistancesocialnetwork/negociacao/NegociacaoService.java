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

    public String checkTrade(String nome, Inventario inventario) throws IOException {
        //TODO implementar a verificação nome existente para ser possivel criar negociacao
        if(rebeldeService.checkRebel(nome)) {
            if(checkListNull(nome, inventario) == null) {
                if (compareTrade(nome, inventario)) {
                    //TODO implementar atualização dos inventarios
                    negociacaoRepository.clearNegociacao(); //Limpa o arquivo de negociacao após o termino
                    return "Negociação realizada! Iventários atualizados com os novos itens.";
                }
            }
            return nome + ", a negociação não é equivalente!";
        }
        return nome + " não existe!";
    }

    public String checkListNull(String nome, Inventario inventario) throws IOException {
        if (negociacaoRepository.listAll().size() == 0) { //Se a lista de negociacao for vazia, cria uma nova negociação
            createTrade(nome, inventario);
            return nome + ", negociação solicitada!";
        }
        return null;
    }

    public void createTrade(String nome, Inventario inventario) throws IOException {
        negociacaoRepository.inserirArquivo(nome, inventario);
    }

    public boolean compareTrade(String nome, Inventario inventario) throws IOException {
        var listNegociacao = negociacaoRepository.listAll();
        //TODO implementar verificação de pontos
        //TODO realizar a comparação, caso os pontos sejam equivalentes: realiza a troca, caso negativo: não realiza
        return false;
    }

}
