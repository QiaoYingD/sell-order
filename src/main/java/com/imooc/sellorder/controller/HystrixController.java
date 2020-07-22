package com.imooc.sellorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 熔断器
 */
@RestController
public class HystrixController {

    @Resource
    RestTemplate restTemplate;


    @GetMapping("/getProductInfo")
    public String getProductInfo(){
        String s = restTemplate.postForObject("http://127.0.0.1:8091/product/getProductForOrder", Arrays.asList("15787519636616022"), String.class);
        return s;
    }

    @RequestMapping("/fallback")
    public String fallBack(){
        return "服务降级,太拥挤了，请稍后再试~`";
    }

}
