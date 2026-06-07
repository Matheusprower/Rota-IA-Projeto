package com.rota.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RotaTest {

    @Test
    public void testCriacaoDeRotaOtimizada() {
        // 1. Preparação (Arrange)
        // Criamos um usuário fictício para amarrar na rota
        Usuario usuarioFicticio = new Usuario();
        usuarioFicticio.setUsername("motorista@teste.com");

        // Instanciamos a Rota com dados de exemplo parecidos com os do seu sistema
        Rota rota = new Rota();
        rota.setOrigem("Belo Horizonte, MG");
        rota.setParadas("Contagem, MG ➔ Betim, MG");
        rota.setDistanciaTotal("35.5 km");
        rota.setTempoTotal("45 min");
        rota.setUsuario(usuarioFicticio);

        // 2. Ação e Verificação (Act & Assert)
        assertNotNull(rota, "O objeto rota não deveria ser nulo");
        assertEquals("Belo Horizonte, MG", rota.getOrigem(), "A origem deve ser a mesma informada");
        assertEquals("Contagem, MG ➔ Betim, MG", rota.getParadas(), "As paradas devem conter a linha otimizada");
        assertEquals("35.5 km", rota.getDistanciaTotal(), "A distância total deve bater");
        assertEquals("45 min", rota.getTempoTotal(), "O tempo estimado deve bater");
        assertEquals("motorista@teste.com", rota.getUsuario().getUsername(), "A rota deve estar associada ao usuário correto");
    }
}
