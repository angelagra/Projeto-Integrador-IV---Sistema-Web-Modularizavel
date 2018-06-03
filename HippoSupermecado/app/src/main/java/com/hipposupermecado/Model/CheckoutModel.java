package com.hipposupermecado.Model;

import java.util.List;

/**
 * Created by Joan on 03/06/2018.
 */

public class CheckoutModel {
    private Long idCliente,idStatus,idTipoPagto,idEndereco,idAplicacao,idPedido;
    private List<ItemCarrinho> itemCarrinho;
    private Boolean action;

    public CheckoutModel(Long idCliente,Long idStatus, Long idTipoPagto, Long idEndereco, Long idAplicacao,List<ItemCarrinho> itemCarrinho) {
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

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Long getIdTipoPagto() {
        return idTipoPagto;
    }

    public void setIdTipoPagto(Long idTipoPagto) {
        this.idTipoPagto = idTipoPagto;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Long getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(Long idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public List<ItemCarrinho> getItemCarrinho() {
        return itemCarrinho;
    }

    public void setItemCarrinho(List<ItemCarrinho> itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }
}
