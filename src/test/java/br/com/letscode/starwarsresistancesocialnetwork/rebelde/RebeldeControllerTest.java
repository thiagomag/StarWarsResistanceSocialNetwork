package br.com.letscode.starwarsresistancesocialnetwork.rebelde;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RebeldeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarTodos() throws Exception {
        this.mockMvc.perform(get("/rebelde"))
                .andDo(print())
                .andExpect(content().string(containsString("Christophsis")))
                .andExpect(status().isOk());
    }

    @Test
    void adicionarRebelde() throws Exception {
        this.mockMvc.perform(post("/rebelde")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"nome\": \"Bodhi Rook\",\n" +
                        "  \"idade\": 25,\n" +
                        "  \"genero\": \"MASCULINO\",\n" +
                        "  \"localizacao\": {\n" +
                        "    \"x\": 100,\n" +
                        "    \"y\": -65,\n" +
                        "    \"z\": -70,\n" +
                        "    \"nomeBase\": \"Jedha\"\n" +
                        "  },\n" +
                        "  \"inventario\": [\n" +
                        "    {\n" +
                        "      \"tipoItem\": \"MUNICAO\",\n" +
                        "      \"qtd\": 20\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void adicionarRebeldeSemNome() throws Exception {
        this.mockMvc.perform(post("/rebelde")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idade\": 25,\n" +
                        "  \"genero\": \"MASCULINO\",\n" +
                        "  \"localizacao\": {\n" +
                        "    \"x\": 100,\n" +
                        "    \"y\": -65,\n" +
                        "    \"z\": -70,\n" +
                        "    \"nomeBase\": \"Jedha\"\n" +
                        "  },\n" +
                        "  \"inventario\": [\n" +
                        "    {\n" +
                        "      \"tipoItem\": \"MUNICAO\",\n" +
                        "      \"qtd\": 20\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(status().isBadRequest());
    }

    @Test
    void adicionarRebeldeSemAlgumAtributo() throws Exception {
        this.mockMvc.perform(post("/rebelde")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idade\": 25,\n" +
                        "  \"genero\": \"MASCULINO\",\n" +
                        "  \"localizacao\": {\n" +
                        "    \"x\": 100,\n" +
                        "    \"z\": -70,\n" +
                        "    \"nomeBase\": \"Jedha\"\n" +
                        "  },\n" +
                        "  \"inventario\": [\n" +
                        "    {\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(status().isBadRequest());
    }

    @Test
    void adicionarRebeldeComGeneroErrado() throws Exception {
        this.mockMvc.perform(post("/rebelde")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"nome\": \"Bodhi Rook\",\n" +
                        "  \"idade\": 25,\n" +
                        "  \"genero\": \"GOY\",\n" +
                        "  \"localizacao\": {\n" +
                        "    \"x\": 100,\n" +
                        "    \"y\": -65,\n" +
                        "    \"z\": -70,\n" +
                        "    \"nomeBase\": \"Jedha\"\n" +
                        "  },\n" +
                        "  \"inventario\": [\n" +
                        "    {\n" +
                        "      \"tipoItem\": \"MUNICAO\",\n" +
                        "      \"qtd\": 20\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException))
                .andExpect(status().isBadRequest());
    }

    @Test
    void adicionarRebeldeComTipoItemErrado() throws Exception {
        this.mockMvc.perform(post("/rebelde")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"nome\": \"Bodhi Rook\",\n" +
                        "  \"idade\": 25,\n" +
                        "  \"genero\": \"MACULINO\",\n" +
                        "  \"localizacao\": {\n" +
                        "    \"x\": 100,\n" +
                        "    \"y\": -65,\n" +
                        "    \"z\": -70,\n" +
                        "    \"nomeBase\": \"Jedha\"\n" +
                        "  },\n" +
                        "  \"inventario\": [\n" +
                        "    {\n" +
                        "      \"tipoItem\": \"VIDEOGAME\",\n" +
                        "      \"qtd\": 20\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarLocalizacao() throws Exception {
        this.mockMvc.perform(patch("/rebelde/atualizaLocal/2a94818f-8112-45ee-883a-ed2bd9968935")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"x\": 480,\n" +
                        "  \"y\": 740,\n" +
                        "  \"z\": 320,\n" +
                        "  \"nomeBase\": \"Tatooine\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void atualizarLocalizacaoComIdIncorreto() throws Exception {
        String exceptionParam = "O id qlqrcoisa informado não consta no nosso banco de dados";
        this.mockMvc.perform(patch("/rebelde/atualizaLocal/qlqrcoisa")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"x\": 480,\n" +
                        "  \"y\": 740,\n" +
                        "  \"z\": 320,\n" +
                        "  \"nomeBase\": \"Tatooine\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof IdRebeldeInvalidoException))
                .andExpect(result -> Assertions.assertEquals(exceptionParam, Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andExpect(status().isNotFound());
    }
}