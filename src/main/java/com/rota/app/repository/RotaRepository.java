package com.rota.app.repository;

import com.rota.app.model.Rota;
import com.rota.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RotaRepository extends JpaRepository<Rota, Long> {
    // Esse comando cria sozinho uma busca no banco: "Ache as rotas deste usuário e ordene por data decrescente"
    List<Rota> findByUsuarioOrderByDataCriacaoDesc(Usuario usuario);
}