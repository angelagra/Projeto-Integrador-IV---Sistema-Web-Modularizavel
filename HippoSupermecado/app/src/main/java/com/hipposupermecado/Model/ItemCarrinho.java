package com.hipposupermecado.Model;

/**
 * Created by Joan on 03/06/2018.
 */

public class ItemCarrinho {
    private Long idProduto;
    private String qtdProduto;
    private String precoItem;

    public ItemCarrinho(Long idProduto, String qtdProduto, String precoItem) {
        this.idProduto = idProduto;
        this.qtdProduto = qtdProduto;
        this.precoItem = precoItem;
    }

    protected ItemCarrinho(){};

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(String qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public String getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(String precoItem) {
        this.precoItem = precoItem;
    }
}
