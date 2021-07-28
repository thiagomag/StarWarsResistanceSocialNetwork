package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NegociacaoRepository {
    private List<Negociacao> registroLinhas = new ArrayList<>();
    private String caminho = "src/main/resources/dados/negociacao.csv";
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

    public void inserirArquivo(String id, List<Inventario> inventario) throws IOException {
        write(format(id, inventario), StandardOpenOption.APPEND);
    }

    private void write(String clienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.flush();
            bf.write(clienteStr);
        }
    }

    public void linhaEmNegociacao() {
        try (Stream<String> streamLinhas = Files.lines(Path.of(caminho))) {
            registroLinhas = streamLinhas
                    .filter(Predicate.not(String::isEmpty))
                    .map(Negociacao::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Negociacao> getAll() {
        linhaEmNegociacao();
        return registroLinhas;
    }

    private String format(String id, List<Inventario> inventario) {
        return String.format("%s,%s\r\n",
                id,
                inventario.toString().replace("[", "").trim().replace("]", "").trim());
    }

    private Inventario convert(String linha) {
        return null;
    }

    public void clearNegociacao() throws IOException {
        Files.newBufferedWriter(path , StandardOpenOption.TRUNCATE_EXISTING);
    }
}
