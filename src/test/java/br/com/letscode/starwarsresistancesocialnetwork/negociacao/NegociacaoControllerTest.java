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

    //NOVA Negociação == Arquivo tem que estar vazio
    @Test
    void adicionaNovaNegociacao() throws Exception {
        this.mockMvc.perform(post("/negociacao/2a94818f-8112-45ee-883a-ed2bd9968935")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"id\": \"a73251f5-6d16-4e24-ab66-ca7cd63969c7\",\n" +
                        "        \"nome\": \"Bodhi Rook\",\n" +
                        "        \"idade\": 25,\n" +
                        "        \"genero\": \"MASCULINO\",\n" +
                        "        \"localizacao\": {\n" +
                        "            \"x\": 100,\n" +
                        "            \"y\": -65,\n" +
                        "            \"z\": -70,\n" +
                        "            \"nomeBase\": \"Jedha\"\n" +
                        "        },\n" +
                        "        \"inventario\": [\n" +
                        "            {\n" +
                        "                \"tipoItem\": \"MUNICAO\",\n" +
                        "                \"qtd\": 1\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Negociacao EXISTENTE no arquivo >> compara a nova entrada
    @Test
    void comparaNegociacaoInvalida() throws Exception {
        this.mockMvc.perform(post("/negociacao/661cd317-804b-49c3-a0ae-2d2422423845")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "    {\n" +
                        "        \"tipoItem\" : \"ARMA\",\n" +
                        "        \"qtd\" : 1\n" +
                        "    }\n" +
                        "]"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void comparaNegociacaoNula() throws Exception {
        this.mockMvc.perform(post("/negociacao/661cd317-804b-49c3-a0ae-2d2422423845")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "    {\n" +
                        "        \"tipoItem\" : ,\n" +
                        "        \"qtd\" : \n" +
                        "    }\n" +
                        "]"))
                .andDo(print())
                .andExpect(result -> Assertions.assertFalse(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(status().isBadRequest());
    }

}
