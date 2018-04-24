
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

@Path("/categoria")
public class CategoriaResource {
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
    private static final String USER = "TSI";
    private static final String PASS = "SistemasInternet123";
    
    @GET
    @Produces("application/json")
    public Response getProdutos() throws ClassNotFoundException, SQLException{
        
        Response response = null;
        Class.forName(DRIVER); // carrega o driver
        
        Connection con = DriverManager.getConnection(URL,USER,PASS); //  conecta ao banco
        
        PreparedStatement stmt = con.prepareStatement("select * from categoria order by nomeCategoria"); // prepara
        
        ResultSet rs = stmt.executeQuery(); // usar executeQuery apara se for consultar
        
        List<Categoria> categorias = new ArrayList<Categoria>();
        
        while(rs.next()){
            Long id = rs.getLong("idCategoria");
            String nome = rs.getString("nomeCategoria");
            String descricao = rs.getString("descCategoria");
            
            Categoria c = new Categoria(id, nome, descricao);
            categorias.add(c);
            
        }
        
        response = Response.ok(categorias).build();
        return response;
        
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProduto(@PathParam("id") Long id) {
        Response response = null;

        return response;
    }
    
}
