package com.nasa.demo.controller;

import com.nasa.demo.service.NasaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NasaController.class)
public class NasaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NasaService nasaService;

    @Test
    public void testGetContentReturnsImage() throws Exception {
        byte[] dummyImage = new byte[]{1, 2, 3, 4};

        when(nasaService.getImage()).thenReturn(dummyImage);

        mockMvc.perform(get("/nasa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(dummyImage));
    }
}

