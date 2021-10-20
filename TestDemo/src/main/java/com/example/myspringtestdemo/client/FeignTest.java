package com.example.myspringtestdemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("feignClientDemo")
public interface FeignTest {
    @GetMapping("/feignClient/encode")
    String encode(@RequestParam String password);
}
