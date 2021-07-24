package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RebeldeService {

    private final RebeldeRepository rebeldeRepository;

    public Rebelde createRebel(Rebelde rebelde) throws IOException {
        return rebeldeRepository.inserirArquivo(rebelde);
    }
}
