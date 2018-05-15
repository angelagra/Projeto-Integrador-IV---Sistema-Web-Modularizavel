/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dgaraujo
 */

@XmlRootElement 
public class Login implements Serializable {
    
    private Long id; 
    private String emailCliente;
    private String senhaCliente;
    private String idcliente;
    private String nomeCompletoCliente;
    
     protected Login() {
         
    }
    //public Login(Long id, String emailCliente, String NomeUsuario, String senhaCliente) {
    public Login(String emailCliente,String senhaCliente,String idcliente) {
         this.emailCliente = emailCliente;
         this.senhaCliente = senhaCliente;
         this.idcliente = idcliente;
     }

 
    @XmlAttribute
     public Long getId() {
        return id;
    }
     public void setId(Long id) {
        this.id = id;
    }
    @XmlElement
    public String getEmailCliente() {
        return emailCliente;
    }
    @XmlElement
    public String getSenhaCliente() {
        return senhaCliente;
    }
    @XmlElement
    public String getIdcliente(){
        return idcliente;
    }
    @XmlElement
    public String getNomeCompletoCliente(){
        return nomeCompletoCliente;
    }
    public String setEmailCliente() {
        return emailCliente;
    }
    
    public String setSenhaCliente() {
        return senhaCliente;
    }
    public String setIdcliente(){
        return idcliente;
    }
    public String setNomeCompletoCliente(){
        return nomeCompletoCliente;
    }
    
}