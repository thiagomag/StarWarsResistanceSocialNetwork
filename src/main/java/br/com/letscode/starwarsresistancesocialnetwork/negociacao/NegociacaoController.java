package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.iventario.TipoItem;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequestMapping("/negociacao")
@RestController
@RequiredArgsConstructor
public class NegociacaoController {

    private final NegociacaoService negociacaoService;
    private final RebeldeService rebeldeService;

    @GetMapping()
    public List<Rebelde> listAll() throws IOException {
        return rebeldeService.listAll();
    }

    //ex: {"nome": "Luiz", "tipoItem" : "ARMA", "qtd" : "1"}
    @PostMapping("{id}")
    public String addNegociacao(@PathVariable String id, @RequestBody List<Inventario> inventario) throws IOException {
        return negociacaoService.checkTrade(id, inventario);
    }
}
