package com.nasa.demo.feignClient;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(url = "https://", name = "get-image-client")
public interface GetImageFeignClient {
    @GetMapping
    Response getImage(URI imageUri);
}
