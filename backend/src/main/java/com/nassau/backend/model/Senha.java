package com.nassau.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_senhas")
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero; 

    @Column(nullable = false)
    private String tipo; 

    @Column(nullable = false)
    private String status; 

    @Column(nullable = false)
    private LocalDateTime dataHoraEmissao = LocalDateTime.now();

    private LocalDateTime dataHoraAtendimento;

    private Integer guiche;

    public Senha() {}

    public Senha(String numero, String tipo, String status) {
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
        this.dataHoraEmissao = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataHoraEmissao() { return dataHoraEmissao; }
    public void setDataHoraEmissao(LocalDateTime dataHoraEmissao) { this.dataHoraEmissao = dataHoraEmissao; }

    public LocalDateTime getDataHoraAtendimento() { return dataHoraAtendimento; }
    public void setDataHoraAtendimento(LocalDateTime dataHoraAtendimento) { this.dataHoraAtendimento = dataHoraAtendimento; }

    public Integer getGuiche() { return guiche; }
    public void setGuiche(Integer guiche) { this.guiche = guiche; }
}