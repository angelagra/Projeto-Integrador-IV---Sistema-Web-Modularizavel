package com.hipposupermecado.Model;

/**
 * Created by Joan on 09/05/2018.
 */

public class LoginModel {
    private String email, senha;

    public LoginModel(String email,String senha){
        this.email = email;
        this.senha = senha;
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
}
