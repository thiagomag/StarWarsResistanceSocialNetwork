package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class RebeldeRepository {

    private String caminho = "src/main/resources/dados/rebeldes.csv";

    private Path path;

    @PostConstruct
    public void init() {
        try {
            path = Paths.get(String.valueOf(caminho));
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Rebelde inserirArquivo(Rebelde rebelde) throws IOException {
        try {
            path = Paths.get(String.valueOf(caminho));
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        write(format(rebelde), StandardOpenOption.APPEND);
        return rebelde;
    }

    private void write(String clienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.flush();
            bf.write(clienteStr);
        }
    }

    public List<Rebelde> listAll() throws IOException {
        List<Rebelde> rebeldes;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            rebeldes = br.lines().filter(Objects::nonNull).filter(Predicate.not(String::isEmpty)).map(this::convert).collect(Collectors.toList());
        }
        return rebeldes;
    }

    private String format(Rebelde rebelde) {
        return String.format("%s,%d,%s,%d,%s,%s,%s\r\n",
                rebelde.getNome(),
                rebelde.getIdade(),
                rebelde.getGenero(),
                rebelde.getQtdReport(),
                rebelde.isTraitor(),
                rebelde.getLocalizacao(),
                rebelde.getIventario().toString().replace("[", "").replace("]", "").trim());
    }

    private Rebelde convert(String linha) {
        return null;
    }
}