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
    
     protected Login() {
         
    }
    //public Login(Long id, String emailCliente, String NomeUsuario, String senhaCliente) {
    public Login(String emailCliente,String senhaCliente) {
         this.emailCliente = emailCliente;
         this.senhaCliente = senhaCliente;
     }

 
    @XmlAttribute
     public Long getId() {
        return id;
    }
     public void setId(Long id) {
        this.id = id;
    }
    @XmlElement
    public String getemailCliente() {
        return emailCliente;
    }
    @XmlElement
    public String getsenhaCliente() {
        return senhaCliente;
    }
}