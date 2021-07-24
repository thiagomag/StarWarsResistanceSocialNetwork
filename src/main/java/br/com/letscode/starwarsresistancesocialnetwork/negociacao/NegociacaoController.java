package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Iventario;
import br.com.letscode.starwarsresistancesocialnetwork.iventario.TipoItem;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RequestMapping("/negociacao")
@RestController
@RequiredArgsConstructor
public class NegociacaoController {

    private final NegociacaoService negociacaoService;

    //ex: {"nome": "Luiz", "tipoItem" : "ARMA", "qtd" : "1"}
    @PostMapping
    public String addNegociacao(@RequestBody ObjectNode objectNode) throws IOException {
        String nome = objectNode.get("nome").asText();
        TipoItem tipoItem = TipoItem.valueOf(objectNode.get("tipoItem").asText());
        int qtd = Integer.parseInt(objectNode.get("qtd").asText());
        Iventario iventario = new Iventario(tipoItem, qtd);
        return negociacaoService.checkTrade(nome, iventario);
    }
}
