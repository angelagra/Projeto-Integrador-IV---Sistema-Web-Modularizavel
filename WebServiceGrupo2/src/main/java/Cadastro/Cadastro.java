/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cadastro;

import java.io.Serializable;
import java.sql.Date;
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
     private String CPFCliente;
     private String celularCliente;
     private String recebeNewsLetter;
     
    protected Cadastro() {
         
    }
      public Cadastro(String nomeCompletoCliente,Date dtNasCliente,String emailCliente,
              String senhaCliente,String CPFCliente,String celularCliente,String recebeNewsLetter) {
          
         this.nomeCompletoCliente = nomeCompletoCliente;
         this.dtNasCliente = dtNasCliente;
         this.emailCliente = emailCliente;
         this.senhaCliente = senhaCliente;
         this.CPFCliente = CPFCliente;
         this.celularCliente = celularCliente;
         this.recebeNewsLetter = recebeNewsLetter;
     }
    
    @XmlAttribute
     public Long getId() {
        return id;
    }
     public void setId(Long id) {
        this.id = id;
    }
    @XmlElement
    public String getnomeCompletoCliente() {
        return nomeCompletoCliente;
    }
    @XmlElement
    public Date getdtNasCliente() {
        return dtNasCliente;
    }
    @XmlElement
    public String getemailCliente() {
        return emailCliente;
    }
    @XmlElement
    public String getsenhaCliente() {
        return senhaCliente;
    }
    @XmlElement
    public String getCPFCliente() {
        return CPFCliente;
    }
    @XmlElement
    public String getcelularCliente() {
        return celularCliente;
    }
    @XmlElement
    public String getrecebeNewsLetter() {
        return recebeNewsLetter;
    }
}
