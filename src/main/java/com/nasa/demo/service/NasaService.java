package com.nasa.demo.service;

import com.nasa.demo.exception.InvalidURLException;
import com.nasa.demo.exception.InvalidInputException;
import com.nasa.demo.feignClient.GetImageFeignClient;
import com.nasa.demo.feignClient.NasaFeignClient;
import com.nasa.demo.model.ResponseNasa;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
public class NasaService {

    private final NasaFeignClient nasaFeignClient;
    private final GetImageFeignClient getImageFeignClient;

    public NasaService(NasaFeignClient nasaFeignClient, GetImageFeignClient getImageFeignClient) {
        this.nasaFeignClient = nasaFeignClient;
        this.getImageFeignClient = getImageFeignClient;
    }


    public byte[] getImage() {
        ResponseNasa responseNasa = nasaFeignClient.getJsonResponse();
        log.info("Get response from nasa" + responseNasa.toString());
        Response response;
        try {
            response = getImageFeignClient.getImage(new URI(responseNasa.getHdurl()));
        } catch (URISyntaxException exception) {
            throw new InvalidURLException("Get invalid Url from nasa service");
        }
            Response.Body body = response.body();
        try (InputStream in = body.asInputStream()){
            return  in.readAllBytes();
        } catch (IOException exception) {
            throw new InvalidInputException("Invalid input: Input cannot be null or empty");
        }
    }
}
