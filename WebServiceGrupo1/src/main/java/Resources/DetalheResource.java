/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
/**
 *
 * @author guilherme.sgarcia
 */
@Path("/detalhes")
public class DetalheResource {
    
          //final significa que nao pode mudar, por ser uma constante, e sempre ser Maiuscula
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
    private static final String USER = "TSI";
    private static final String PASS = "SistemasInternet123";

    @GET
    @Produces("application/json")
    public Response getDetalhes(){
        Response response = null;
       //Class.forName(DRIVER);   // carregar o driver
       // Connection comn =  DriverManager.getConnection(URL, USER, PASS); 
       try(Connection comn = getConnection();
        PreparedStatement stmt =  comn.prepareStatement("select * from produto order by nomeProduto"); //PREPARA 
        ResultSet rs =  stmt.executeQuery()){ // quando so vai consultar faz query
       
       List<Detalhe> detalhes = new ArrayList<Detalhe>(); // lista que guarda categoria
       
       while(rs.next()){ //enquanto tiver proximo, pego os dados 
           Long id     = rs.getLong("idProduto"); 
           String nome = rs.getString("nomeProduto");
           String descricao = rs.getString("descProduto");
           Double preco = rs.getDouble("precProduto");
           Double desconto = rs.getDouble("descontoPromocao");
           Long idCategoria     = rs.getLong("idCategoria"); 
           
           Detalhe d = new Detalhe(id,nome,descricao,preco,desconto, idCategoria); //cria a categoria
           detalhes.add(d); // adiciona na lista 
       }
       
       response = Response.ok(detalhes).build();
       }catch(ClassNotFoundException | SQLException ex){
            response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Serviço Invalido").build();
        }
        return response;
    }
    
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
                
                    
                    Long idP = rs.getLong("idProduto"); 
                    String nome = rs.getString("nomeProduto");
                    String descricao = rs.getString("descProduto");
                    Double preco = rs.getDouble("precProduto");
                    Double desconto = rs.getDouble("descontoPromocao");
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
