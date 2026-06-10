package com.rota.app.repository;

import com.rota.app.model.Rota;
import com.rota.app.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DatabaseAndHistoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RotaRepository rotaRepository;

    @Test
    void testBancoDeDadosSalvarEBuscarUsuario() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername("testususario");
        novoUsuario.setPassword("senha123");
        novoUsuario.setNome("Teste teste");

        Usuario salvo = usuarioRepository.save(novoUsuario);

        assertThat(salvo.getId()).isNotNull();

        Usuario encontrado = usuarioRepository.findByUsername("testususario");
        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getNome()).isEqualTo("Teste teste");
    }

    @Test
    void testBuscarHistoricoUsuarioIdMaisBaixo() {
        Usuario user1 = new Usuario();
        user1.setUsername("user_baixo");
        user1.setPassword("senha1");
        user1.setNome("User com ID Baixo");
        user1 = usuarioRepository.save(user1);

        Usuario user2 = new Usuario();
        user2.setUsername("user_alto");
        user2.setPassword("senha2");
        user2.setNome("User com ID Alto");
        user2 = usuarioRepository.save(user2);

        assertThat(user1.getId()).isLessThan(user2.getId());

        Rota rota1 = new Rota();
        rota1.setOrigem("Ponto A");
        rota1.setParadas("Ponto B");
        rota1.setDistanciaTotal("10km");
        rota1.setTempoTotal("15min");
        rota1.setDataCriacao(LocalDateTime.now().minusHours(1));
        rota1.setUsuario(user1);
        rotaRepository.save(rota1);

        Rota rota2 = new Rota();
        rota2.setOrigem("Ponto C");
        rota2.setParadas("Ponto D");
        rota2.setDistanciaTotal("20km");
        rota2.setTempoTotal("30min");
        rota2.setDataCriacao(LocalDateTime.now());
        rota2.setUsuario(user1);
        rotaRepository.save(rota2);

        Rota rota3 = new Rota();
        rota3.setOrigem("Ponto E");
        rota3.setParadas("Ponto F");
        rota3.setDistanciaTotal("5km");
        rota3.setTempoTotal("10min");
        rota3.setUsuario(user2);
        rotaRepository.save(rota3);

        List<Usuario> todosUsuarios = usuarioRepository.findAll();
        Usuario usuarioIdMaisBaixo = todosUsuarios.stream()
                .min((u1, u2) -> u1.getId().compareTo(u2.getId()))
                .orElseThrow();

        assertThat(usuarioIdMaisBaixo.getId()).isEqualTo(user1.getId());

        List<Rota> historico = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuarioIdMaisBaixo);

        assertThat(historico).hasSize(2);
        assertThat(historico.get(0).getOrigem()).isEqualTo("Ponto C");
        assertThat(historico.get(1).getOrigem()).isEqualTo("Ponto A");
    }
}
