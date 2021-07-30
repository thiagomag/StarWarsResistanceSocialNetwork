package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

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

@Repository
public class ReportarRepository {

    private final String caminho = "src/main/resources/dados/reportes.csv";

    private Path path;

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

    public void inserirArquivo(Reportar reportar) throws IOException {
        write(format(reportar));
    }

    private void write(String reportarStr) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
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
