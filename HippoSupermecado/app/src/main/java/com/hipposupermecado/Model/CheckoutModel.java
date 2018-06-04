package com.hipposupermecado.Model;

import java.util.List;

/**
 * Created by Joan on 03/06/2018.
 */

public class CheckoutModel {
    private Long idCliente,idPedido;
    private int idStatus,idTipoPagto,idAplicacao,idEndereco;
    private List<Carrinho> itemCarrinho;
    private Boolean action;

    public CheckoutModel(Long idCliente,int idStatus, int idTipoPagto, int idEndereco, int idAplicacao,List<Carrinho> itemCarrinho) {
        this.idCliente = idCliente;
        this.idStatus = idStatus;
        this.idTipoPagto = idTipoPagto;
        this.idEndereco = idEndereco;
        this.idAplicacao = idAplicacao;
        this.itemCarrinho = itemCarrinho;
    }

    public CheckoutModel(Boolean action) {
        this.action = action;
    }

    public CheckoutModel(Boolean action, Long idPedido){
        this.action = action;
        this.idPedido = idPedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdTipoPagto() {
        return idTipoPagto;
    }

    public void setIdTipoPagto(int idTipoPagto) {
        this.idTipoPagto = idTipoPagto;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(int idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public List<Carrinho> getItemCarrinho() {
        return itemCarrinho;
    }

    public void setItemCarrinho(List<Carrinho> itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }
}
