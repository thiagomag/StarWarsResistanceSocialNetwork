package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import br.com.letscode.starwarsresistancesocialnetwork.localizacao.Localizacao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Rebelde adicionarRebelde(@Valid @RequestBody Rebelde rebelde) throws IOException {
            return rebeldeService.createRebel(rebelde);
    }

    @PatchMapping("/atualizaLocal/{id}")
    public Optional<Rebelde> atualizarLocalizacao(@RequestBody Localizacao localizacao, @PathVariable String id) throws IOException {
        return rebeldeService.updateLocalization(localizacao, id);
    }
}