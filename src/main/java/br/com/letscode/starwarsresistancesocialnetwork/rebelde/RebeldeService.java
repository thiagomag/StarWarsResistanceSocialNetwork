package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RebeldeService {

    private final RebeldeRepository rebeldeRepository;

    public Rebelde createRebel(Rebelde rebelde) throws IOException {
        rebelde.setId(UUID.randomUUID().toString());
        return rebeldeRepository.inserirArquivo(rebelde);
    }

    public boolean checkRebel(String id) throws IOException {
        List<Rebelde> listaRebeldes = listAll();
        Optional<Rebelde> rebelde = listaRebeldes.stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(id)).findFirst();
        return rebelde.isPresent();
    }

    @SneakyThrows
    public Rebelde returnRebel(String id) {
        if(!checkRebel(id)){return null;}
        for (Rebelde rebelde : listAll()){
            if (rebelde.getId().equals(id)){return rebelde;}
        }
        return null;
    }

    public List<Rebelde> listAll() throws IOException {
        return rebeldeRepository.listAll();
    }

    public Optional<Rebelde> updateLocalization(Localizacao localizacao, String id) throws IOException {
        List<Rebelde> listaRebeldes = listAll();
        Optional<Rebelde> rebelde = listaRebeldes.stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(id)).findFirst();
        if(rebelde.isPresent()) {
            rebelde.get().setLocalizacao(localizacao);
            rebeldeRepository.reescreverArquivo(listaRebeldes);
        } else {
            throw new IdRebeldeInvalidoException(id);
        }
        return rebelde;
    }

    public int valorInventario(Inventario inventario) {
        return (inventario.getArma()*4) + (inventario.getMunicao()*3)
                + (inventario.getAgua()*2) + inventario.getComida();
    }
}