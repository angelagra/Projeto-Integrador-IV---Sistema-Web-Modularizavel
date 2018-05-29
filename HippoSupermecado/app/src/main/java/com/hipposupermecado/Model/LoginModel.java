package com.hipposupermecado.Model;

public class LoginModel {
    private String email, senha,nome;
    private Boolean action;
    private Long id;

    public LoginModel(String email,String senha){
        this.email = email;
        this.senha = senha;
    }

    public LoginModel(Boolean action){
        this.action = action;
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

    public Boolean getAction() {return action;}

    public void setAction(Boolean action) {this.action = action;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
//commit