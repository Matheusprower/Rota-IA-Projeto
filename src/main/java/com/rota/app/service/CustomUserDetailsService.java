package com.rota.app.service;

import com.rota.app.model.Usuario;
import com.rota.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo login (email/username)
        Usuario usuario = repository.findByUsername(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado no banco de dados!");
        }
        
        // Transforma o seu Usuario do banco em um usuário que o Spring Security entende
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Essa senha já virá criptografada do banco
                .roles("USER")
                .build();
    }
}