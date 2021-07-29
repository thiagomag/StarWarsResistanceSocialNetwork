package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.Rebelde;
import br.com.letscode.starwarsresistancesocialnetwork.rebelde.RebeldeRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportarRepository {

    private File file;
    private final RebeldeRepository rebeldeRepository;

    public ReportarRepository(@Value("${files.reportes}") String fileName, RebeldeRepository rebeldeRepository) {
        this.rebeldeRepository = rebeldeRepository;
        try {
            this.file = new ClassPathResource(fileName).getFile();
            if (!file.exists()) {
                Files.createFile(Path.of(file.getPath()));
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
        findRebelde(reportar, rebeldesList);
        rebeldeRepository.reescreverArquivo(rebeldesList);
        inserirArquivo(reportar);
        return "rebelde reportado.";
    }

    private void findRebelde(Reportar reportar, List<Rebelde> rebeldesList) {
        for (Rebelde rebelde : rebeldesList) {
            if (reportar.getIdTraidor().equals(rebelde.getId())) {
                rebelde.setQtdReport(rebelde.getQtdReport()+1);
                if (rebelde.getQtdReport() == 3) {rebelde.setTraitor(true);}
            }
        }
    }

    public List<Reportar> listReportes() throws IOException {
        List<Reportar> reportes;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
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
        write(format(reportar));
    }

    private void write(String reportarStr) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(Path.of(file.getPath()), StandardOpenOption.APPEND)) {
            bf.flush();
            bf.write(reportarStr);
        }
    }

    private String format(Reportar reportar) {
        return String.format("%s,%s\r\n",
                reportar.getIdDenunciante(),
                reportar.getIdTraidor());
    }
}
