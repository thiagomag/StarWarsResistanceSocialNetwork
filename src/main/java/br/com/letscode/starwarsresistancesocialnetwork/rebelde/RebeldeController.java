package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/rebelde")
@RestController
@RequiredArgsConstructor
public class RebeldeController {

    private final RebeldeService rebeldeService;

    @PostMapping
    public Rebelde addRebel(@RequestBody Rebelde rebelde) throws IOException {
        return rebeldeService.createRebel(rebelde);
    }
}
