package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Rebelde> listAll() throws IOException {
        return rebeldeService.listAll();
    }

    @PostMapping("{id}")
    public String addNegociacao(@PathVariable String id, @RequestBody Rebelde rebelde) throws IOException {
        return negociacaoService.checkTrade(id, rebelde.getInventario());
    }

}
