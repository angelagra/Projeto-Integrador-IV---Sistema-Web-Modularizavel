package com.hipposupermecado.Model;

/**
 * Created by Noah on 29/05/2018.
 */

public class Carrinho {
    private int id;
    private String nome;
    private double preco, desconto;
    private int qtd;

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Carrinho(int id, String nome, double preco, double desconto, int qtd) {

        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.desconto = desconto;
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
}
