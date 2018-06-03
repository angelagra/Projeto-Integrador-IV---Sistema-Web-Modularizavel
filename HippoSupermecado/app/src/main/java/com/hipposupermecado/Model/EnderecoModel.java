package com.hipposupermecado.Model;

import com.hipposupermecado.Endereco;

/**
 * Created by Joan on 09/05/2018.
 */

public class EnderecoModel {
    private String nomeEndereco, logradouroEndereco,numeroEndereco,cepEndereco,cidadeEndereco,paisEndereco,UFEndereco,complementoEndereco;
    private Boolean action;
    private Long id;

    public EnderecoModel(String nomeEndereco, String logradouroEndereco, String numeroEndereco, String cepEndereco, String cidadeEndereco, String paisEndereco, String uf,String complemento,Long id) {
        this.nomeEndereco = nomeEndereco;
        this.logradouroEndereco = logradouroEndereco;
        this.numeroEndereco = numeroEndereco;
        this.cepEndereco = cepEndereco;
        this.cidadeEndereco = cidadeEndereco;
        this.paisEndereco = paisEndereco;
        this.UFEndereco = uf;
        this.complementoEndereco = complemento;
        this.id = id;
    }

    public EnderecoModel(Boolean action){
        this.action = action;
    }

    public EnderecoModel(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEndereco() {
        return nomeEndereco;
    }

    public void setNomeEndereco(String nomeEndereco) {
        this.nomeEndereco = nomeEndereco;
    }

    public String getLogradouroEndereco() {
        return logradouroEndereco;
    }

    public void setLogradouroEndereco(String logradouroEndereco) {
        this.logradouroEndereco = logradouroEndereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(String cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getPaisEndereco() {
        return paisEndereco;
    }

    public void setPaisEndereco(String paisEndereco) {
        this.paisEndereco = paisEndereco;
    }

    public String getUFEndereco() {
        return UFEndereco;
    }

    public void setUFEndereco(String UFEndereco) {
        this.UFEndereco = UFEndereco;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }
}