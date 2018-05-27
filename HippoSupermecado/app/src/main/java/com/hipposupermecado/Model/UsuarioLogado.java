package com.hipposupermecado.Model;

public class UsuarioLogado {

    private Long id = null;
    private String nome = null;
    private boolean estaLogado = false;

    public boolean getEstaLogado() { return estaLogado; }

    public void setEstaLogado(boolean estaLogado) { this.estaLogado = estaLogado; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
