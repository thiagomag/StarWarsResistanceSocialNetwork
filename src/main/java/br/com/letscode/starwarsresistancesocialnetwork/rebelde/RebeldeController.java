package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/rebelde")
@RestController
@RequiredArgsConstructor
public class RebeldeController {

    private final RebeldeService rebeldeService;

    @GetMapping
    public List<Rebelde> listarTodos() throws IOException {
        return rebeldeService.listAll();
    }

    @PostMapping
    public Rebelde adicionarRebelde(@RequestBody Rebelde rebelde) throws IOException {
        return rebeldeService.createRebel(rebelde);
    }

    @PatchMapping("/atualizaLocal")
    public Optional<Rebelde> atualizarLocalizacao(@RequestBody Localizacao localizacao, @PathParam(value = "nome") String nome) throws IOException {
        return rebeldeService.updateLocalization(localizacao, nome);
    }
}
