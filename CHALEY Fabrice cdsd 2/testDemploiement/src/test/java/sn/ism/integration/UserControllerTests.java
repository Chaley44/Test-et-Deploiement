package sn.ism.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))  // 3 utilisateurs initialement dans la liste
                .andExpect(jsonPath("$.[0].name").value("Jordane"));
    }

    @Test
    public void createUser() throws Exception {
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Saint\"," +
                                "\"adresse\":\"Saint@gmail.com\"," +
                                "\"password\":\"password\"" +
                                "}")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Saint"));
    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(
                put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Jordan Updated\"," +
                                "\"adresse\":\"jordan.updated@gmail.com\"," +
                                "\"password\":\"newpassword\"" +
                                "}")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Jordan Updated"))
        .andExpect(jsonPath("$.adresse").value("jordan.updated@gmail.com"))
        .andExpect(jsonPath("$.password").value("newpassword"));
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }
}
