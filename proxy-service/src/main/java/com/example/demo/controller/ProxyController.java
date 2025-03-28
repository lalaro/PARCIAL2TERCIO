package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ProxyController {

    @Value("${function.service.instances}")
    private List<String> functionServiceInstances;

    private final AtomicInteger counter = new AtomicInteger(0);
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/factors")
    public Object proxyFactors(@RequestParam int value) {
        String selectedInstance = getNextInstance();
        String url = selectedInstance + "/factors?value=" + value;
        return restTemplate.getForObject(url, Object.class);
    }

    @GetMapping("/primes")
    public Object proxyPrimes(@RequestParam int value) {
        String selectedInstance = getNextInstance();
        String url = selectedInstance + "/primes?value=" + value;
        return restTemplate.getForObject(url, Object.class);
    }

    private String getNextInstance() {
        int index = counter.getAndIncrement() % functionServiceInstances.size();
        return functionServiceInstances.get(index);
    }
}