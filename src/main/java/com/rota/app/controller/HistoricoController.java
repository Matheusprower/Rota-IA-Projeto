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

    // Retorna o histórico em formato JSON para o JavaScript renderizar no mapa
    @GetMapping("/api/rotas")
    @ResponseBody
    public ResponseEntity<List<Rota>> listarRotas() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        List<Rota> rotas = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuario);
        return ResponseEntity.ok(rotas);
    }

    // SALVAR ROTA 
    @PostMapping("/api/rotas/salvar")
    @ResponseBody
    public ResponseEntity<?> salvarRota(@RequestBody Map<String, Object> dados) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Usuario usuario = usuarioRepository.findByUsername(username);

            Rota rota = new Rota();
            rota.setOrigem((String) dados.get("origem"));
            rota.setParadas((String) dados.get("paradas"));
            rota.setDistanciaTotal((String) dados.get("distanciaTotal"));
            rota.setTempoTotal((String) dados.get("tempoTotal"));
            rota.setUsuario(usuario);

            rotaRepository.save(rota);

            return ResponseEntity.ok().body(Map.of("status", "sucesso"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("status", "erro", "mensagem", e.getMessage()));
        }
    }

    // EXPORTAR HISTÓRICO (.JSON)
    @GetMapping("/api/rotas/exportar")
    public ResponseEntity<byte[]> exportarRotas() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());

            List<Rota> rotas = rotaRepository.findByUsuarioOrderByDataCriacaoDesc(usuario);

            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            String json = mapper.writeValueAsString(rotas);

            byte[] jsonBytes = json.getBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", "meu_historico_rotas.json");

            return ResponseEntity.ok().headers(headers).body(jsonBytes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // IMPORTAR HISTÓRICO redireciona de volta ao Mapa
    @PostMapping("/api/rotas/importar")
    public String importarRotas(@RequestParam("file") MultipartFile file) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());

            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            List<Rota> rotasImportadas = mapper.readValue(file.getInputStream(), new TypeReference<List<Rota>>() {
            });

            for (Rota rota : rotasImportadas) {
                rota.setId(null);
                rota.setUsuario(usuario);
                rotaRepository.save(rota);
            }
        } catch (Exception e) {
            System.out.println("Erro ao importar: " + e.getMessage());
        }

        // REDIRECIONA PARA O MAPA DIRETAMENTE
        return "redirect:/mapa?importSuccess";
    }
}
