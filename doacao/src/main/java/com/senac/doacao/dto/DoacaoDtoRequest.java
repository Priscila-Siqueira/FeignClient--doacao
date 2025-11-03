package com.senac.doacao.dto;

import java.time.LocalDate;

public class DoacaoDtoRequest {

    private String nomeDoador;
    private String emailDoador;
    private Integer doacaoValor;

    // getters e setters
    public String getNomeDoador() { return nomeDoador; }
    public void setNomeDoador(String nomeDoador) { this.nomeDoador = nomeDoador; }

    public String getEmailDoador() { return emailDoador; }
    public void setEmailDoador(String emailDoador) { this.emailDoador = emailDoador; }

    public Integer getDoacaoValor() { return doacaoValor; }
    public void setDoacaoValor(Integer doacaoValor) { this.doacaoValor = doacaoValor; }
}
