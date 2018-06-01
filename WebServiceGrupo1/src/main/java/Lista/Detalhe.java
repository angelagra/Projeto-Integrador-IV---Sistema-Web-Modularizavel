package Lista;

/**
 *
 * @author guilherme.sgarcia
 */
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Detalhe implements Serializable {

    private Long id, idCategoria; 
    private String nome;
    private String descricao;
    private Double preco;
    private Double desconto;

    protected Detalhe() {
    }

    public Detalhe(Long id, String nome, String descricao, Double preco, Double desconto, Long idCategoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.desconto = desconto;
        this.idCategoria = idCategoria;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public String getNome() {
        return nome;
    }

    @XmlElement
    public String getDescricao() {
        return descricao;
    }
    @XmlAttribute
    public Double getPreco(){
        return preco;
    }
    @XmlAttribute
    public Double getDesconto(){
        return desconto;
    }

    @XmlAttribute
    public Long getIdCategoria(){
        return idCategoria;
    }
}

