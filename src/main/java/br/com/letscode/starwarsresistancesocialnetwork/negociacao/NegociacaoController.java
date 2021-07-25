package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.iventario.TipoItem;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/negociacao")
@RestController
@RequiredArgsConstructor
public class NegociacaoController {

    private final NegociacaoService negociacaoService;
    private final RebeldeService rebeldeService;

    @GetMapping()
    public List listAll() throws IOException {
        return rebeldeService.listAll();
    }

    //ex: {"nome": "Luiz", "tipoItem" : "ARMA", "qtd" : "1"}
    @PostMapping
    public String addNegociacao(@RequestBody ObjectNode objectNode) throws IOException {
        String id = objectNode.get("id").asText();
        TipoItem tipoItem = TipoItem.valueOf(objectNode.get("tipoItem").asText());
        int qtd = Integer.parseInt(objectNode.get("qtd").asText());
        Inventario inventario = new Inventario(tipoItem, qtd);
        return negociacaoService.checkTrade(id, inventario);
    }
}
