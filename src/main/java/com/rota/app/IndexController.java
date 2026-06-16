package com.rota.app;

import com.rota.app.model.Usuario;
import com.rota.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @GetMapping("/mapa")
    public String telaMapa(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        // Passa o nome real para o template Thymeleaf
        if (usuario != null && usuario.getNome() != null) {
            model.addAttribute("nomeReal", usuario.getNome());
        } else {
            model.addAttribute("nomeReal", username);
        }

        return "mapa";
    }
}
