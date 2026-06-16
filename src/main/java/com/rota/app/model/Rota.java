package com.rota.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "rotas")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origem;

    @Column(columnDefinition = "TEXT")
    private String paradas;

    private String distanciaTotal;
    private String tempoTotal;

    // CORREÇÃO BUG 3: @JsonFormat instrui o Jackson a serializar LocalDateTime
    // como string ISO, sem depender de módulo externo para funcionar.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

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
