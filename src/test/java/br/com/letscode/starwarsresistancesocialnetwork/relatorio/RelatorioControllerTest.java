package br.com.letscode.starwarsresistancesocialnetwork.relatorio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RelatorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void traidores() throws Exception {
        this.mockMvc.perform(get("/relatorio/traidores"))
                .andDo(print())
                .andExpect(content().string(containsString("Porcentagem de traidores:")))
                .andExpect(status().isOk());
    }

    @Test
    void rebeldes() throws Exception {
        this.mockMvc.perform(get("/relatorio/rebeldes"))
                .andDo(print())
                .andExpect(content().string(containsString("Porcentagem de rebeldes:")))
                .andExpect(status().isOk());
    }

    @Test
    void pontosPerdidos() throws Exception {
        this.mockMvc.perform(get("/relatorio/pontos"))
                .andDo(print())
                .andExpect(content().string(containsString("pontos devido a traidores")))
                .andExpect(status().isOk());
    }

    @Test
    void recursos() throws Exception {
        this.mockMvc.perform(get("/relatorio/recursos"))
                .andDo(print())
                .andExpect(content().string(containsString("aguas por rebelde")))
                .andExpect(status().isOk());
    }
}