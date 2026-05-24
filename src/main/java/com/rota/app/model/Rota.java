package com.rota.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rotas")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origem;
    
    @Column(columnDefinition = "TEXT")
    private String paradas; // Vamos salvar os destinos extras aqui

    private String distanciaTotal;
    private String tempoTotal;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    // Aqui está a mágica: Liga esta rota ao usuário que está logado
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // --- GETTERS E SETTERS MANUAIS (Para evitar aquele erro de symbol do Lombok) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getParadas() { return paradas; }
    public void setParadas(String paradas) { this.paradas = paradas; }

    public String getDistanciaTotal() { return distanciaTotal; }
    public void setDistanciaTotal(String distanciaTotal) { this.distanciaTotal = distanciaTotal; }

    public String getTempoTotal() { return tempoTotal; }
    public void setTempoTotal(String tempoTotal) { this.tempoTotal = tempoTotal; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}