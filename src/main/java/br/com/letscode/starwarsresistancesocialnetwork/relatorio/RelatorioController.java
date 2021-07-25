package br.com.letscode.starwarsresistancesocialnetwork.relatorio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RequestMapping("/relatorio")
@RestController
@RequiredArgsConstructor
public class RelatorioController {

    final RelatorioService relatorioService;

    @GetMapping("/traidores")
    public String traidores() throws IOException {
        return relatorioService.traidoresReport();
    }

    @GetMapping("/rebeldes")
    public String rebeldes() throws IOException {
        return relatorioService.rebeldesReport();
    }

    @GetMapping("/pontos")
    public String pontosPerdidos() throws IOException {
        return relatorioService.pontosReport();
    }

    @GetMapping("/recursos")
    public String recursos() throws IOException {
        return relatorioService.recursosReport();
    }
}
