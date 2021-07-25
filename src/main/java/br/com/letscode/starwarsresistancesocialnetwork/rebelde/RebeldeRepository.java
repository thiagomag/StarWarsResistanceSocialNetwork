package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.iventario.TipoItem;
import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
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

    public void reescreverArquivo(List<Rebelde> listaRebeldes) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (Rebelde rebeldeBuilder : listaRebeldes) {
            builder.append(format(rebeldeBuilder));
        }
        write(builder.toString(), StandardOpenOption.CREATE);
    }

    private String format(Rebelde rebelde) {
        return String.format("%s,%s,%d,%s,%d,%s,%s,%s\r\n",
                rebelde.getId(),
                rebelde.getNome(),
                rebelde.getIdade(),
                rebelde.getGenero(),
                rebelde.getQtdReport(),
                rebelde.isTraitor(),
                rebelde.getLocalizacao(),
                rebelde.getInventario().toString().replace("[", "").trim().replace("]", "").trim());
    }

    private Rebelde convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ",");
        var rebelde = Rebelde.builder()
                .id(token.nextToken())
                .nome(token.nextToken())
                .idade(Integer.valueOf(token.nextToken()))
                .genero(GeneroRebelde.valueOf(token.nextToken()))
                .qtdReport(Integer.valueOf(token.nextToken()))
                .isTraitor(Boolean.parseBoolean(token.nextToken()))
                .localizacao(Localizacao.builder()
                        .x(Long.valueOf(token.nextToken()))
                        .y(Long.valueOf(token.nextToken()))
                        .z(Long.valueOf(token.nextToken()))
                        .nomeBase(token.nextToken())
                        .build())
                .build();
        List<Inventario> inventarioList = new ArrayList<>();
        while (token.hasMoreTokens()) {
            var inventario = Inventario.builder()
                    .tipoItem(TipoItem.valueOf(token.nextToken().trim()))
                    .qtd(Integer.parseInt(token.nextToken().trim()))
                    .build();
            inventarioList.add(inventario);
        }
        rebelde.setInventario(inventarioList);
        return rebelde;
    }
}
