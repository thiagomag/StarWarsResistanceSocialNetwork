package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Iventario;
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
public class NegociacaoRepository {

    private final String caminho = "src/main/resources/dados/negociacao.csv";

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

    public void inserirArquivo(String nome, Iventario iventario) throws IOException {
        try {
            path = Paths.get(String.valueOf(caminho));
            if (!path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        write(format(nome, iventario), StandardOpenOption.APPEND);
    }

    private void write(String clienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.flush();
            bf.write(clienteStr);
        }
    }

    public List<Iventario> listAll() throws IOException {
        List<Iventario> iventario;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            iventario = br.lines().filter(Objects::nonNull).filter(Predicate.not(String::isEmpty)).map(this::convert).collect(Collectors.toList());
        }
        return iventario;
    }

    private String format(String nome, Iventario iventario) {
        return String.format("%s,%d,%s\r\n",
                nome,
                iventario.getQtd(),
                iventario.getTipoItem());
    }

    private Iventario convert(String linha) {
        return null;
    }

    public void clearNegociacao() throws IOException {
        Files.newBufferedWriter(path , StandardOpenOption.TRUNCATE_EXISTING);
    }
}
