
package Lista;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produtos implements Serializable {
    
    private Long id, idCategoria;
    private String nome, descricao ;
    private double preco, desconto;
    private int qtdMinEstoque;
    //private   imagem;
    
    protected Produtos(){
        
    }
    
    public Produtos(Long id, String nome, String descricao, double preco, double desconto, int qtdMinEstoque){
        
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.desconto = desconto;
        this.qtdMinEstoque = qtdMinEstoque;
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
    
    @XmlElement
    public Double getPreco(){
        return preco;
    }
    
    @XmlElement
    public Double getDesconto(){
        return desconto;
    }
    
    @XmlElement
    public int getQtdEstoque(){
        return qtdMinEstoque;
    }
    
}