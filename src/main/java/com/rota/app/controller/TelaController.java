package com.rota.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelaController {

    @GetMapping("/")
    public String telaInicio() {
        return "index";
    }
}