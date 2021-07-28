package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.iventario.Inventario;
import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
import br.com.letscode.starwarsresistancesocialnetwork.negociacao.Negociacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
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

    public void receiveTrade(List<Negociacao> rebelde1UpdateInventario, List<Negociacao> rebelde2UpdateInventario) throws IOException {
        List<Rebelde> listaRebeldes = listAll();
        Optional<Rebelde> rebelde1 = listaRebeldes.stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(rebelde1UpdateInventario.get(0).getId())).findFirst();
        if(rebelde1.isPresent()) {
            rebelde1.get().setInventario(updateInventario(rebelde1, rebelde1UpdateInventario, rebelde2UpdateInventario));
            rebeldeRepository.reescreverArquivo(listaRebeldes);
        }
        Optional<Rebelde> rebelde2 = listaRebeldes.stream().filter(rebeldeSearch -> rebeldeSearch.getId().equals(rebelde2UpdateInventario.get(0).getId())).findFirst();
        if(rebelde2.isPresent()) {
            rebelde2.get().setInventario(updateInventario(rebelde2, rebelde2UpdateInventario, rebelde1UpdateInventario));
            rebeldeRepository.reescreverArquivo(listaRebeldes);
        }
    }

    public List<Inventario> updateInventario(Optional<Rebelde> rebelde, List<Negociacao> rebeldeOldInventario, List<Negociacao> rebeldeNewInventario){
        List<Inventario> antigoInventario = new ArrayList<>();
        for (Negociacao negociacao : rebeldeOldInventario) {
            antigoInventario.add(new Inventario(negociacao.getTipoItem(), negociacao.getQtd()));
        }
        List<Inventario> novoInventario = new ArrayList<>();
        for (Negociacao negociacao : rebeldeNewInventario) {
            novoInventario.add(new Inventario(negociacao.getTipoItem(), negociacao.getQtd()));
        }
        recalculaInventario(rebelde, antigoInventario, novoInventario);
        return rebelde.get().getInventario();
    }

    private void recalculaInventario(Optional<Rebelde> rebelde, List<Inventario> antigoInventario, List<Inventario> novoInventario) {
        removeItensAntigos(rebelde, antigoInventario);
        adicionaItensNovos(rebelde, antigoInventario, novoInventario);
    }

    private void adicionaItensNovos(Optional<Rebelde> rebelde, List<Inventario> antigoInventario, List<Inventario> novoInventario) {
        for (int i = 0; i < rebelde.get().getInventario().size(); i++){
            for (int j = 0; j < novoInventario.size(); j++) {
                if (rebelde.get().getInventario().get(i).getTipoItem().equals(novoInventario.get(j).getTipoItem())){
                    rebelde.get().getInventario().get(i).setQtd((rebelde.get().getInventario().get(j).getQtd() + antigoInventario.get(i).getQtd()));
                } else {
                    rebelde.get().getInventario().get(i).setTipoItem(novoInventario.get(j).getTipoItem());
                    rebelde.get().getInventario().get(i).setQtd(novoInventario.get(j).getQtd());
                }
            }
        }
    }

    private void removeItensAntigos(Optional<Rebelde> rebelde, List<Inventario> antigoInventario) {
        for (int i = 0; i < rebelde.get().getInventario().size(); i++){
            for (int j = 0; j < antigoInventario.size(); j++) {
                if (rebelde.get().getInventario().get(i).getTipoItem().equals(antigoInventario.get(j).getTipoItem())){
                    rebelde.get().getInventario().get(i)
                            .setQtd((rebelde.get().getInventario().get(i).getQtd() - antigoInventario.get(j).getQtd()));
                }
            }
        }
    }
}