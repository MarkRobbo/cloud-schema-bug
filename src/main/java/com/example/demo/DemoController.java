package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class DemoController {

    private final WebClient webClient;

    @Autowired
    public DemoController(final WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("http://localhost:8080")
            .build();
    }

    @GetMapping("/1")
    public String endpoint1() {
        return "1";
    }

    @GetMapping("/2")
    public String endpoint2() {
        return "2";
    }

    @GetMapping("/3")
    public String endpoint3() {
        return "3";
    }

    @GetMapping("/call")
    public String callInSequence() {
        String first = webClient.get()
            .uri("/1")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        String second = webClient.get()
            .uri("/2")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        String third = webClient.get()
            .uri("/3")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return first + second + third;
    }
}
