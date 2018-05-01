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
    private String loginUsuario;
    private String NomeUsuario;
    private String senhaUsuario;
    
     protected Login() {
         
    }
    //public Login(Long id, String loginUsuario, String NomeUsuario, String senhaUsuario) {
    public Login(String loginUsuario,String senhaUsuario) {
         this.loginUsuario = loginUsuario;
         this.senhaUsuario = senhaUsuario;
     }

 
    @XmlAttribute
     public Long getId() {
        return id;
    }
     public void setId(Long id) {
        this.id = id;
    }
    @XmlElement
    public String getloginUsuario() {
        return loginUsuario;
    }
    @XmlElement
    public String getNomeUsuario() {
        return NomeUsuario;
    }
    @XmlElement
    public String getsenhaUsuario() {
        return senhaUsuario;
    }
}