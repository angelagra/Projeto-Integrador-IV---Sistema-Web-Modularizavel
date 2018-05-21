/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cadastro;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dgaraujo
 */
@XmlRootElement
public class Cadastro implements Serializable {
    
     private Long id; 
     private String nomeCompletoCliente;
     private Date dtNasCliente;
     private String emailCliente;
     private String senhaCliente;
     private String cpfCliente;
     private String celularCliente;
     private String telComercialCliente;
     private String telResidencialCliente;
     private String recebeNewsLetter;
     
     
  // SimpleDateFormat format = new SimpleDateFormat(pattern);
   
    
    protected Cadastro() {
         
    }
      public Cadastro(String nomeCompletoCliente,String emailCliente,
                      String senhaCliente,String cpfCliente,String celularCliente,String telComercialCliente,
                      String telResidencialCliente,Date dtNasCliente,String recebeNewsLetter) {
          
         this.nomeCompletoCliente = nomeCompletoCliente;
         this.emailCliente = emailCliente;
         this.senhaCliente = senhaCliente;
         this.cpfCliente = cpfCliente;
         this.celularCliente = celularCliente;
         this.telComercialCliente = telComercialCliente;
         this.telResidencialCliente = telResidencialCliente;
         this.dtNasCliente = dtNasCliente;
         this.recebeNewsLetter = recebeNewsLetter;
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompletoCliente() {
        return nomeCompletoCliente;
    }

    public void setNomeCompletoCliente(String nomeCompletoCliente) {
        this.nomeCompletoCliente = nomeCompletoCliente;
    }

    public Date getDtNasCliente() {
        return dtNasCliente;
    
    }

    public void setDtNasCliente(Date dtNasCliente) {
        this.dtNasCliente = dtNasCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
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

    public String getRecebeNewsLetter() {
        return recebeNewsLetter;
    }

    public void setRecebeNewsLetter(String recebeNewsLetter) {
        this.recebeNewsLetter = recebeNewsLetter;
    }


    
  
}  
