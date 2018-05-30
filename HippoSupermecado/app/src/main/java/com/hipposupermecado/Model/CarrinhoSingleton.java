package com.hipposupermecado.Model;


import java.util.ArrayList;
import java.util.List;

public class CarrinhoSingleton {
    private static final CarrinhoSingleton INSTANCE = new CarrinhoSingleton();

    private List<Carrinho> produto = new ArrayList<Carrinho>();
    private CarrinhoSingleton() {}

    public List<Carrinho> getProdutos() {
        return produto;
    }

    public void setProduto(Carrinho produto) {
        this.produto.add(produto);
    }

    public void deleteProduto(int i) {this.produto.remove(i);}

    public static CarrinhoSingleton getInstance() {
        return INSTANCE;
    }
}
