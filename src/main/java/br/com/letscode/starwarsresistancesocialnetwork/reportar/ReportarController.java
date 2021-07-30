package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/reportar")
@RestController
@RequiredArgsConstructor
public class ReportarController {

    private final ReportarService reportarService;

    @PostMapping
    public String reportarRebelde(@RequestBody Reportar reportar) throws IOException {
        return reportarService.reportarRebelde(reportar);
    }
}