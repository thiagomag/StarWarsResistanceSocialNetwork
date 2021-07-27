package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.IdRebeldeInvalidoException;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportarService {

    private final ReportarRepository reportarRepository;
    private final RebeldeRepository rebeldeRepository;

    public String reportarRebelde(Reportar reportar) throws IOException {
        Optional<Rebelde> rebeldeTraidor = rebeldeRepository.listAll().stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(reportar.getIdTraidor())).findFirst();
        Optional<Rebelde> rebeldeDenunciante = rebeldeRepository.listAll().stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(reportar.getIdDenunciante())).findFirst();
        if (rebeldeTraidor.isEmpty()) {
            throw new IdRebeldeInvalidoException(reportar.getIdTraidor());
        }  else if (rebeldeDenunciante.isEmpty()) {
            throw new IdRebeldeInvalidoException(reportar.getIdDenunciante());
        } else if (reportar.getIdTraidor().equals(reportar.getIdDenunciante())){
            throw new ReportarASiMesmoException();
        } else if (rebeldeTraidor.get().isTraitor()) {
            throw new RebeldeJaEhTraidor(reportar.getIdTraidor());
        } else {
            return reportarRepository.reportarRebelde(reportar);
        }
    }
}
