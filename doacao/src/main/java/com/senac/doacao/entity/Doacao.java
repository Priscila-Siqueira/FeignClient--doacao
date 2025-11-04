package com.senac.doacao.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doacao_id")
    private Integer id;
    @Column(name = "doacao_data")
    private LocalDateTime data;
    @Column(name = "doacao_valor")
    private Integer valor;
    @Column(name = "doacao_status")
    private Integer status;
    @Column(name = "doador_id")
    private Integer doadorId;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDoadorId() {
        return doadorId;
    }

    public void setDoadorId(Integer doadorId) {
        this.doadorId = doadorId;
    }
}
