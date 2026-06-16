package com.rota.app.controller;

import com.rota.app.model.Rota;
import com.rota.app.model.Usuario;
import com.rota.app.repository.RotaRepository;
import com.rota.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
public class HistoricoController {

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cria um ObjectMapper configurado para lidar com LocalDateTime corretamente
    private ObjectMapper criarMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @GetMapping("/api/rotas")
    @ResponseBody
    public ResponseEntity<List<Rota>> listarRotas() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());
            if (usuario == null) {
                System.err.println("[ERRO] Usuário não encontrado: " + auth.getName());
                return ResponseEntity.status(401).build();
            }
            List<Rota> rotas = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuario);
            return ResponseEntity.ok(rotas);
        } catch (Exception e) {
            System.err.println("[ERRO] listarRotas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // CORREÇÃO BUG 3: retorno tipado como Map<String,Object> evita ambiguidade
    // de serialização e garante que o JSON chegue correto ao JavaScript.
    @PostMapping("/api/rotas/salvar")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> salvarRota(@RequestBody Map<String, Object> dados) {
        Map<String, Object> resposta = new HashMap<>();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            System.out.println("[INFO] Salvando rota para: " + username);

            Usuario usuario = usuarioRepository.findByUsername(username);
            if (usuario == null) {
                System.err.println("[ERRO] Usuário não encontrado ao salvar: " + username);
                resposta.put("status", "erro");
                resposta.put("mensagem", "Usuário não encontrado. Faça login novamente.");
                return ResponseEntity.status(401).body(resposta);
            }

            Rota rota = new Rota();
            rota.setOrigem((String) dados.get("origem"));
            rota.setParadas((String) dados.get("paradas"));
            rota.setDistanciaTotal((String) dados.get("distanciaTotal"));
            rota.setTempoTotal((String) dados.get("tempoTotal"));
            rota.setUsuario(usuario);

            Rota salva = rotaRepository.save(rota);
            System.out.println("[INFO] Rota salva com ID: " + salva.getId());

            resposta.put("status", "sucesso");
            resposta.put("id", salva.getId());
            return ResponseEntity.ok(resposta);

        } catch (Exception e) {
            System.err.println("[ERRO] salvarRota: " + e.getMessage());
            e.printStackTrace();
            resposta.put("status", "erro");
            resposta.put("mensagem", e.getMessage());
            return ResponseEntity.status(500).body(resposta);
        }
    }

    @GetMapping("/api/rotas/exportar")
    public ResponseEntity<byte[]> exportarRotas() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());

            List<Rota> rotas = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuario);

            byte[] jsonBytes = criarMapper().writeValueAsBytes(rotas);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", "meu_historico_rotas.json");

            return ResponseEntity.ok().headers(headers).body(jsonBytes);
        } catch (Exception e) {
            System.err.println("[ERRO] exportarRotas: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/api/rotas/importar")
    public String importarRotas(@RequestParam("file") MultipartFile file) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());

            List<Rota> rotasImportadas = criarMapper()
                    .readValue(file.getInputStream(), new TypeReference<List<Rota>>() {});

            for (Rota rota : rotasImportadas) {
                rota.setId(null);
                rota.setUsuario(usuario);
                rotaRepository.save(rota);
            }
        } catch (Exception e) {
            System.err.println("[ERRO] importarRotas: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/mapa?importSuccess";
    }
}
