package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ReportarService {

    private final ReportarRepository reportarRepository;

    public String reportarRebelde(Reportar reportar) throws IOException {
        if (reportar.getIdTraidor().equals(reportar.getIdDenunciante())){
            return "Você não pode reportar a si mesmo!";
        }
        else {
            return reportarRepository.reportarRebelde(reportar);
        }
    }
}
