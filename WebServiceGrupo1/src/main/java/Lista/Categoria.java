
package Lista;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Categoria implements Serializable {
    
    private Long id;
    private String nome, descricao ;
    
    protected Categoria(){
        
    }
    
    public Categoria(Long id, String nome, String descricao){
        
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
           
    @XmlAttribute
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    @XmlElement
    public String getNome(){
        return nome;
    }
    
    @XmlElement
    public String getDescricao(){
        return descricao;
    }
    
   
}