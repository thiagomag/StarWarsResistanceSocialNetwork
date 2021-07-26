package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import br.com.letscode.starwarsresistancesocialnetwork.rebelde.IdRebeldeInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void reportarRebelde() throws Exception {
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"2a94818f-8112-45ee-883a-ed2bd9968935\",\n" +
                        "  \"idTraidor\": \"661cd317-804b-49c3-a0ae-2d2422423845\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("rebelde reportado.")));
    }

    @Test
    void reportarASiMesmo() throws Exception {
        String exceptionParam = "Você não pode reportar a si mesmo.";
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"2a94818f-8112-45ee-883a-ed2bd9968935\",\n" +
                        "  \"idTraidor\": \"2a94818f-8112-45ee-883a-ed2bd9968935\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ReportarASiMesmoException))
                .andExpect(result -> Assertions.assertEquals(exceptionParam, Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andExpect(status().isConflict());
    }

    @Test
    void reporteJaFeitoAnteriormente() throws Exception {
        String exceptionParam = "Você já reportou o rebelde com id 661cd317-804b-49c3-a0ae-2d2422423845 anteriormente";
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"2a94818f-8112-45ee-883a-ed2bd9968935\",\n" +
                        "  \"idTraidor\": \"661cd317-804b-49c3-a0ae-2d2422423845\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof RebeldeReportadoAnteriormenteException))
                .andExpect(result -> Assertions.assertEquals(exceptionParam, Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andExpect(status().isAlreadyReported());
    }

    @Test
    void reporteComIdTraidorErrado() throws Exception {
        String exceptionParam = "O id qlqrcoisa informado não consta no nosso banco de dados";
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"qlqrcoisa\",\n" +
                        "  \"idTraidor\": \"dfe7294d-1433-4cf8-af67-55035aec9eab\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof IdRebeldeInvalidoException))
                .andExpect(result -> Assertions.assertEquals(exceptionParam, Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andExpect(status().isNotFound());
    }

    @Test
    void reporteComIdDenuncianteErrado() throws Exception {
        String exceptionParam = "O id qlqrcoisa informado não consta no nosso banco de dados";
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"dfe7294d-1433-4cf8-af67-55035aec9eab\",\n" +
                        "  \"idTraidor\": \"qlqrcoisa\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof IdRebeldeInvalidoException))
                .andExpect(result -> Assertions.assertEquals(exceptionParam, Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andExpect(status().isNotFound());
    }

    @Test
    void rebeldeJaEhTraidor() throws Exception {
        String exceptionParam = "O rebelde com o id dfe7294d-1433-4cf8-af67-55035aec9eab já está registrado como traidor.";
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"ddcb531e-6c13-4267-bd96-e74eb55debeb\",\n" +
                        "  \"idTraidor\": \"dfe7294d-1433-4cf8-af67-55035aec9eab\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof RebeldeJaEhTraidor))
                .andExpect(result -> Assertions.assertEquals(exceptionParam, Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andExpect(status().isAlreadyReported());

    }
}