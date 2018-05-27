package com.hipposupermecado.Model;

public class LoginModel {
    private String email, senha;
    private Boolean action;

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
}
//commit