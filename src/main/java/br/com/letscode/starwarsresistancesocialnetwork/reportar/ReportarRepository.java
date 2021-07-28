package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeRepository;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportarRepository {

    private final String caminho = "src/main/resources/dados/reportes.csv";

    private Path path;

    private final RebeldeRepository rebeldeRepository;

    @PostConstruct
    public void init() {
        try {
            path = Paths.get(caminho);
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String reportarRebelde(Reportar reportar) throws IOException {
        List<Rebelde> rebeldesList = rebeldeRepository.listAll();
        if (checarSeJaFoiReportado(reportar)){
            throw new RebeldeReportadoAnteriormenteException(reportar.getIdTraidor());
        }
        for (Rebelde rebelde : rebeldesList) {
            if (reportar.getIdTraidor().equals(rebelde.getId())) {
                rebelde.setQtdReport(rebelde.getQtdReport()+1);
                if (rebelde.getQtdReport() == 3) {rebelde.setTraitor(true);}
            }
        }
        rebeldeRepository.reescreverArquivo(rebeldesList);
        inserirArquivo(reportar);
        return "rebelde reportado.";
    }

    public List<Reportar> listReportes() throws IOException {
        List<Reportar> reportes;
        try (CSVReader reader = new CSVReader(new FileReader(caminho))) {
            reportes = new ArrayList<>();
            String[] line;
            while ((line = reader.readNext()) != null) {
                var reportar = new Reportar(line[0],line[1]);
                reportes.add(reportar);
            }
        }
        return reportes;
    }

    public boolean checarSeJaFoiReportado(Reportar reportar) throws IOException {
        for (Reportar reporte : listReportes()) {
            if (reportar.equals(reporte)){return true;}
        }
        return false;
    }

    public void inserirArquivo(Reportar reportar) throws IOException {
        try {
            path = Paths.get(caminho);
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        write(format(reportar));
    }

    private void write(String clienteStr) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bf.flush();
            bf.write(clienteStr);
        }
    }

    private String format(Reportar reportar) {
        return String.format("%s,%s\r\n",
                reportar.getIdDenunciante(),
                reportar.getIdTraidor());
    }
}
