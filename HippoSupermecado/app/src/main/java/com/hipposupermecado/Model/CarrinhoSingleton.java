package com.hipposupermecado.Model;


import java.util.ArrayList;
import java.util.List;

public class CarrinhoSingleton {
    private static final CarrinhoSingleton INSTANCE = new CarrinhoSingleton();

    private List<Produto> produto = new ArrayList<Produto>();
    private CarrinhoSingleton() {}

    public List<Produto> getProdutos() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto.add(produto);
    }

    public static CarrinhoSingleton getInstance() {
        return INSTANCE;
    }
}
