
package Resources;

import Lista.Categoria;
import Lista.Produtos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/produtos")
public class ProdutosResources {
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
    private static final String USER = "TSI";
    private static final String PASS = "SistemasInternet123";
    
    @GET
    @Produces("application/json")
    public Response getProdutos() throws ClassNotFoundException, SQLException{
        
        Response response = null;
        
        try(Connection conn = getConnection();
               PreparedStatement stmt = conn.prepareStatement("select * from produto order by nomeProduto");
                ResultSet rs = stmt.executeQuery()){ /*usar executeQuery apara se for consultar*/          

            List<Produtos> produtos = new ArrayList<>();
            
            while(rs.next()){
                Long id = rs.getLong("idProduto");
                String nome = rs.getString("nomeProduto");
                String descricao = rs.getString("descProduto");
                double preco = rs.getDouble("precProduto");
                double desconto = rs.getDouble("descontoPromocao");
                int estoque = rs.getInt("qtdMinEstoque");
                Long categoria =  rs.getLong("idCategoria");

                Produtos p = new Produtos(id, nome, descricao, preco, desconto, estoque,categoria);
                produtos.add(p);

            }

            response = Response.ok(produtos).build();
        }catch(ClassNotFoundException | SQLException ex){
            response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Serviço Invalido").build();
        }
        return response;
        
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProduto(@PathParam("id") Long id) {
        Response response = null;

        return response;
    }
    
    private static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USER,PASS);
    }
    
}
