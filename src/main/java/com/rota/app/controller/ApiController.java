package com.rota.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rota.app.service.ApiService;

@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/teste")
    public String getApi() {
        return apiService.buscarDados();
    }

    @PostMapping("/otimizar")
    public String otimizarRota(@RequestBody List<String> enderecos) {
        // Envia a lista para o serviço processar
        return apiService.processarMelhorRota(enderecos);
    }
}