package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/negociacao")
@RestController
@RequiredArgsConstructor
public class NegociacaoController {

    private final NegociacaoService negociacaoService;

    @PostMapping()
    public String addNegociacao( @RequestBody Negociacao negociacao) {
        return negociacaoService.negociacao(negociacao);
    }

}
