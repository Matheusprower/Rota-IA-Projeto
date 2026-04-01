package com.rota.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/posts")
                .build();
    }

    public String buscarDados() {
        return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String processarMelhorRota(List<String> enderecos) {
        // Por enquanto, apenas confirmamos o recebimento dos dados
        System.out.println("Endereços recebidos no Service: " + enderecos);
        return "Sucesso! O Java recebeu " + enderecos.size() + " endereços para otimização.";
    }
}