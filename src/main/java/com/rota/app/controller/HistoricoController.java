package com.rota.app.controller;

import com.rota.app.model.Rota;
import com.rota.app.model.Usuario;
import com.rota.app.repository.RotaRepository;
import com.rota.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;

@Controller
public class HistoricoController {

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/historico")
    public String verHistorico(Model model) {
        // 1. Identifica qual usuário do sistema está logado no momento
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Usuario usuario = usuarioRepository.findByUsername(username);

        // 2. Busca no banco de dados as rotas pertencentes EXCLUSIVAMENTE a esse usuário
        List<Rota> rotas = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuario);

        // 3. Envia a lista de rotas para a tela HTML do Thymeleaf
        model.addAttribute("rotas", rotas);

        return "historico";
    }

    @PostMapping("/api/rotas/salvar")
    @ResponseBody
    public ResponseEntity<?> salvarRota(@RequestBody Map<String, Object> dados) {
        try {
            // 1. Pega o usuário logado
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Usuario usuario = usuarioRepository.findByUsername(username);

            // 2. Cria o objeto Rota com os dados vindos da tela
            Rota rota = new Rota();
            rota.setOrigem((String) dados.get("origem"));
            rota.setParadas((String) dados.get("paradas"));
            rota.setDistanciaTotal((String) dados.get("distanciaTotal"));
            rota.setTempoTotal((String) dados.get("tempoTotal"));
            rota.setUsuario(usuario);

            // 3. Salva no banco de dados do Docker
            rotaRepository.save(rota);

            return ResponseEntity.ok().body(Map.of("status", "sucesso"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("status", "erro", "mensagem", e.getMessage()));
        }
    }

    // === 1. EXPORTAR HISTÓRICO (GERA O ARQUIVO .JSON) ===
    @GetMapping("/api/rotas/exportar")
    public ResponseEntity<byte[]> exportarRotas() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());

            // Busca rotas do usuário
            List<Rota> rotas = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuario);

            // Transforma a lista do Java em texto JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules(); // Suporte para o LocalDateTime
            String json = mapper.writeValueAsString(rotas);

            // Prepara o arquivo para download
            byte[] jsonBytes = json.getBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", "meu_historico_rotas.json");

            return ResponseEntity.ok().headers(headers).body(jsonBytes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // === 2. IMPORTAR HISTÓRICO (LÊ O ARQUIVO E SALVA NO BANCO) ===
    @PostMapping("/api/rotas/importar")
    public String importarRotas(@RequestParam("file") MultipartFile file) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());

            // Lê o arquivo JSON e transforma de volta em uma lista de Rotas
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            List<Rota> rotasImportadas = mapper.readValue(file.getInputStream(), new TypeReference<List<Rota>>() {
            });

            // Salva cada rota no banco de dados do novo computador
            for (Rota rota : rotasImportadas) {
                rota.setId(null); // Reseta o ID para o MySQL criar um novo
                rota.setUsuario(usuario); // Associa as rotas ao usuário logado nesta máquina
                rotaRepository.save(rota);
            }
        } catch (Exception e) {
            System.out.println("Erro ao importar: " + e.getMessage());
        }

        // Atualiza a página de histórico
        return "redirect:/historico";
    }
}
