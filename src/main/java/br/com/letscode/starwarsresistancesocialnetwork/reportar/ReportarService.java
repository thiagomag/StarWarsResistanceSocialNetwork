package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.IdRebeldeInvalidoException;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportarService {

    private final ReportarRepository reportarRepository;
    private final RebeldeRepository rebeldeRepository;

    public String reportarRebelde(Reportar reportar) throws IOException {
        List<Rebelde> rebeldesList = rebeldeRepository.listAll();
        Optional<Rebelde> rebeldeTraidor = rebeldeRepository.listAll().stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(reportar.getIdTraidor())).findFirst();
        Optional<Rebelde> rebeldeDenunciante = rebeldeRepository.listAll().stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(reportar.getIdDenunciante())).findFirst();
        checkExceptions(reportar, rebeldeTraidor, rebeldeDenunciante);
        if (checkIfReported(reportar)){
            throw new RebeldeReportadoAnteriormenteException(reportar.getIdTraidor());
        }
        findRebelde(reportar, rebeldesList);
        rebeldeRepository.reescreverArquivo(rebeldesList);
        reportarRepository.inserirArquivo(reportar);
        return "rebelde reportado.";
    }

    private void checkExceptions(Reportar reportar, Optional<Rebelde> rebeldeTraidor, Optional<Rebelde> rebeldeDenunciante) {
        if (rebeldeTraidor.isEmpty()) {
            throw new IdRebeldeInvalidoException(reportar.getIdTraidor());
        } else if (rebeldeDenunciante.isEmpty()) {
            throw new IdRebeldeInvalidoException(reportar.getIdDenunciante());
        } else if (reportar.getIdTraidor().equals(reportar.getIdDenunciante())){
            throw new ReportarASiMesmoException();
        } else if (rebeldeTraidor.get().isTraitor()) {
            throw new RebeldeJaEhTraidor(reportar.getIdTraidor());
        }
    }

    private boolean checkIfReported(Reportar reportar) throws IOException {
        for (Reportar reporte : reportarRepository.listReportes()) {
            if (reportar.equals(reporte)){return true;}
        }
        return false;
    }

    private void findRebelde(Reportar reportar, List<Rebelde> rebeldesList) {
        for (Rebelde rebelde : rebeldesList) {
            if (reportar.getIdTraidor().equals(rebelde.getId())) {
                rebelde.setQtdReport(rebelde.getQtdReport()+1);
                if (rebelde.getQtdReport() == 3) {rebelde.setTraitor(true);}
            }
        }
    }
}
