package ru;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.service.library.DemoApplication;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class DemoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBookResponseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/"))
                .andExpect(status().isOk());

    }
}
