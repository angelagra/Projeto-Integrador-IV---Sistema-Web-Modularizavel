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
public class LoginEnv implements Serializable{
    
    
    private String idcliente;
    private String nomeCompletoCliente;

    protected LoginEnv() {
         
    }
     public LoginEnv(String idcliente,String nomeCompletoCliente) {
         this.idcliente = idcliente;
         this.nomeCompletoCliente = nomeCompletoCliente;
       
     }
  
    @XmlElement
    public String getIdcliente(){
        return idcliente;
    }
    @XmlElement
    public String getNomeCompletoCliente(){
        return nomeCompletoCliente;
    }
    public String setIdcliente(){
        return idcliente;
    }
    public String setNomeCompletoCliente(){
        return nomeCompletoCliente;
    }
}
