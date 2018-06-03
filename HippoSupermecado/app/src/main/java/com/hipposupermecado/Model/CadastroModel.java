package com.hipposupermecado.Model;

/**
 * Created by Joan on 10/05/2018.
 */

public class CadastroModel {
    private String login,senha,nome,cpfCliente,celularCliente,telComercialCliente,telResidencialCliente,data,recebeNewsLetter;
    private Boolean action;
    private Long idUsuario;

    public CadastroModel(String login, String senha, String nome, String cpfCliente, String celularCliente, String telComercialCliente, String telResidencialCliente, String data, String recebeNewsLetter) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpfCliente = cpfCliente;
        this.celularCliente = celularCliente;
        this.telComercialCliente = telComercialCliente;
        this.telResidencialCliente = telResidencialCliente;
        this.data = data;
        this.recebeNewsLetter = recebeNewsLetter;
    }

    public CadastroModel(Boolean action,Long idUsuario){
        this.action = action;
        this.idUsuario = idUsuario;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getTelComercialCliente() {
        return telComercialCliente;
    }

    public void setTelComercialCliente(String telComercialCliente) {
        this.telComercialCliente = telComercialCliente;
    }

    public String getTelResidencialCliente() {
        return telResidencialCliente;
    }

    public void setTelResidencialCliente(String telResidencialCliente) {
        this.telResidencialCliente = telResidencialCliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRecebeNewsLetter() {
        return recebeNewsLetter;
    }

    public void setRecebeNewsLetter(String recebeNewsLetter) {
        this.recebeNewsLetter = recebeNewsLetter;
    }
}