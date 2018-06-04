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
        if (!checkProduto(produto))
            this.produto.add(produto);
    }

    public boolean checkProduto(Carrinho produto) {
        boolean add = false;
        for(int i = 0; i < this.produto.size(); i++) {
            if(this.produto.get(i).getId() == produto.getId()) {
                this.produto.get(i).setQtd(this.produto.get(i).getQtd() + produto.getQtd());
                add = true;
                break;
            }
        }
        return add;
    }

    public void deleteProduto(int i) {this.produto.remove(i);}

    public static CarrinhoSingleton getInstance() {
        return INSTANCE;
    }
}
