package com.rota.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String telaLogin() {
        return "login"; // aponta para login.html
    }

    @GetMapping("/mapa")
    public String telaMapa() {
        return "mapa"; // aponta para mapa.html
    }

    /*@GetMapping("/")
    public String raiz() {
        return "redirect:/mapa"; // Vai forçar o login primeiro
    }*/
}