package Resources;

import Lista.Detalhe;
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

@Path("/detalhes")
public class DetalheResource {
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
    private static final String USER = "TSI";
    private static final String PASS = "SistemasInternet123";

        
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getDetalhe(@PathParam("id") Long id) {
        Response response = null;

        try (Connection comn = getConnection();
                PreparedStatement stmt = comn.prepareStatement("select * from produto where idProduto = ?")) {
            stmt.setLong(1, id);
            
            List<Detalhe> detalhes = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                
                while(rs.next()){
                    
                    Long idP         = rs.getLong("idProduto"); 
                    String nome      = rs.getString("nomeProduto");
                    String descricao = rs.getString("descProduto");
                    Double preco     = rs.getDouble("precProduto");
                    Double desconto  = rs.getDouble("descontoPromocao");
                    Long idCategoria = rs.getLong("idCategoria");

                    Detalhe d = new Detalhe(idP, nome, descricao, preco,desconto,idCategoria );
                    detalhes.add(d);
                    
                
                    //response = Response.status(Response.Status.NOT_FOUND).entity(id).build();
                }
                response = Response.ok(detalhes).build();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ex == null ? "?" : ex.getMessage()).build();
        }

        return response;
    }
    
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
