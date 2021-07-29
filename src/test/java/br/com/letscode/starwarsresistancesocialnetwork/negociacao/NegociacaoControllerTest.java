package br.com.letscode.starwarsresistancesocialnetwork.negociacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NegociacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listAll() throws Exception {
        this.mockMvc.perform(get("/negociacao"))
                .andDo(print())
                .andExpect(content().string(containsString("1")))
                .andExpect(status().isOk());
    }

    @Test
    void negociacaoRealizada() throws Exception {
        this.mockMvc.perform(post("/negociacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idRebelde1\": \"f78b007d-d2b0-4ff2-8192-04f460fd5e77\",\n" +
                        "  \"inventario1\": {\n" +
                        "    \"arma\": 1,\n" +
                        "    \"agua\": 1\n" +
                        "  },\n" +
                        "  \"idRebelde2\": \"9146af65-794c-4ba8-a3cb-0e3fb5d794bb\",\n" +
                        "  \"inventario2\": {\n" +
                        "    \"municao\": 2\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void negociacaoNaoCompativel() throws Exception {
        this.mockMvc.perform(post("/negociacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idRebelde1\": \"f78b007d-d2b0-4ff2-8192-04f460fd5e77\",\n" +
                        "  \"inventario1\": {\n" +
                        "    \"arma\": 2,\n" +
                        "    \"agua\": 2\n" +
                        "  },\n" +
                        "  \"idRebelde2\": \"9146af65-794c-4ba8-a3cb-0e3fb5d794bb\",\n" +
                        "  \"inventario2\": {\n" +
                        "    \"municao\": 2,\n" +
                        "    \"agua\": 2\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void negociacaoQtdItemInvalida() throws Exception {
        this.mockMvc.perform(post("/negociacao/661cd317-804b-49c3-a0ae-2d2422423845")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idRebelde1\": \"f78b007d-d2b0-4ff2-8192-04f460fd5e77\",\n" +
                        "  \"inventario1\": {\n" +
                        "    \"arma\": 50,\n" +
                        "  },\n" +
                        "  \"idRebelde2\": \"9146af65-794c-4ba8-a3cb-0e3fb5d794bb\",\n" +
                        "  \"inventario2\": {\n" +
                        "    \"agua\": 100\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void negociacaoNula() throws Exception {
        this.mockMvc.perform(post("/negociacao/661cd317-804b-49c3-a0ae-2d2422423845")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idRebelde1\": \"f78b007d-d2b0-4ff2-8192-04f460fd5e77\",\n" +
                        "  \"inventario1\": {\n" +
                        "    \"arma\": 2,\n" +
                        "    \"agua\": \n" +
                        "  },\n" +
                        "  \"idRebelde2\": \"9146af65-794c-4ba8-a3cb-0e3fb5d794bb\",\n" +
                        "  \"inventario2\": {\n" +
                        "    \"agua\": 2\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
