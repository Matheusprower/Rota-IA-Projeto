package com.rota.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import com.rota.app.model.Usuario;
import com.rota.app.repository.UsuarioRepository;

@Controller
public class TelaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String telaInicio() {
        return "login";
    }

    @GetMapping("/mapa")
    public String telaMapa(Model model, Principal principal) {
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByUsername(principal.getName());
            if (usuario != null) {
                model.addAttribute("nomeReal", usuario.getNome());
            }
        }
        return "mapa";
    }
}