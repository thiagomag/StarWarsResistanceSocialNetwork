package br.com.letscode.starwarsresistancesocialnetwork.reportar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                        "  \"idTraidor\": \"dfe7294d-1433-4cf8-af67-55035aec9eab\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("rebelde reportado.")));
    }
    @Test
    void reportarASiMesmo() throws Exception {
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"2a94818f-8112-45ee-883a-ed2bd9968935\",\n" +
                        "  \"idTraidor\": \"2a94818f-8112-45ee-883a-ed2bd9968935\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Você não pode reportar a si mesmo!")));
    }
    @Test
    void reporteJaFeitoAnteriormente() throws Exception {
        this.mockMvc.perform(post("/reportar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"idDenunciante\": \"ddcb531e-6c13-4267-bd96-e74eb55debeb\",\n" +
                        "  \"idTraidor\": \"dfe7294d-1433-4cf8-af67-55035aec9eab\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("rebelde ja foi reportado anteriormente!")));
    }
}