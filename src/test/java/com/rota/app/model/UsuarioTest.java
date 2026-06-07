package com.rota.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioTest {

    @Test
    public void testCriacaoDeUsuario() {
        // 1. Preparação (Arrange)
        Usuario usuario = new Usuario();
        usuario.setNome("João da Silva");
        usuario.setUsername("joao@email.com");
        usuario.setPassword("senhaSegura123");

        // 2. Ação e Verificação (Act & Assert)
        assertNotNull(usuario, "O objeto usuário não deveria ser nulo");
        assertEquals("João da Silva", usuario.getNome(), "O nome do usuário deve ser igual ao inserido");
        assertEquals("joao@email.com", usuario.getUsername(), "O username deve ser o email inserido");
    }
}
