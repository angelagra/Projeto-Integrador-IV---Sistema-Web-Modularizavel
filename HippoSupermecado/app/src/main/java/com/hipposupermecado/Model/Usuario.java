package com.hipposupermecado.Model;

/**
 * Created by Joan on 10/05/2018.
 */

public class Usuario {
    private String nome,email,senha,confSenha;

    public Usuario(String nome, String email, String senha, String confSenha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confSenha = confSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }
}
