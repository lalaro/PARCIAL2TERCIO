package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class FunctionController {

    @GetMapping("/factors")
    public FunctionResponse factors(@RequestParam int value) {
        List<Integer> factors = calculateFactors(value);
        return new FunctionResponse("factors", value,
                factors.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "))
        );
    }

    @GetMapping("/primes")
    public FunctionResponse primes(@RequestParam int value) {
        List<Integer> primes = calculatePrimes(value);
        return new FunctionResponse("primes", value,
                primes.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "))
        );
    }

    private List<Integer> calculateFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    private List<Integer> calculatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int num = 2; num <= n; num++) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        return primes;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Clase interna para respuesta JSON
    public static class FunctionResponse {
        private String operation;
        private int input;
        private String output;

        public FunctionResponse(String operation, int input, String output) {
            this.operation = operation;
            this.input = input;
            this.output = output;
        }

        // Getters para serialización JSON
        public String getOperation() { return operation; }
        public int getInput() { return input; }
        public String getOutput() { return output; }
    }
}