package com.nasa.demo.feignClient;

import com.nasa.demo.model.ResponseNasa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.cloud.openfeign.FeignClient(url = "${client.url}", name = "nasa-feignClient")
public interface NasaFeignClient {
    @GetMapping
    ResponseNasa getJsonResponse();
}
