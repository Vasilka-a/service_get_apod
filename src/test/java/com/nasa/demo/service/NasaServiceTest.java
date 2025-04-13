package com.nasa.demo.service;

import com.nasa.demo.feignClient.GetImageFeignClient;
import com.nasa.demo.feignClient.NasaFeignClient;
import com.nasa.demo.model.ResponseNasa;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NasaServiceTest {

    private NasaFeignClient nasaFeignClient;
    private GetImageFeignClient getImageFeignClient;
    private NasaService nasaService;

    @BeforeEach
    void setup() {
        nasaFeignClient = mock(NasaFeignClient.class);
        getImageFeignClient = mock(GetImageFeignClient.class);
        nasaService = new NasaService(nasaFeignClient, getImageFeignClient);
    }

    @Test
    void testGetContentReturnsBytes() {
        String testUrl = "https://apod.nasa.gov/apod/image/test.jpg";
        String image = "image.jpeg";
        byte[] expectedBytes = image.getBytes();

        ResponseNasa responseNasa = new ResponseNasa();
        responseNasa.setHdurl(testUrl);

        when(nasaFeignClient.getJsonResponse()).thenReturn(responseNasa);

        when(getImageFeignClient.getImage(URI.create(responseNasa.getHdurl()))).thenReturn(Response.builder()
                .status(1)
                .headers(new HashMap<>())
                .reason("")
                .request(Request.create(Request.HttpMethod.GET, image, new HashMap<>(), expectedBytes, null))
                .protocolVersion(null)
                .body(expectedBytes).build());

        byte[] nasaImage = nasaService.getImage();

        assertArrayEquals(expectedBytes, nasaImage);
    }
}
