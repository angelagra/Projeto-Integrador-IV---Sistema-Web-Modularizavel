/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CategoriaResource;

/**
 *
 * @author leonardo.prodrigues6
 */
import Categoria.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/categorias")
public class CategoriaResource {

        //final significa que nao pode mudar, por ser uma constante, e sempre ser Maiuscula
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
    private static final String USER = "TSI";
    private static final String PASS = "SistemasInternet123";

    @GET
    @Produces("application/json")
    public Response getCategorias() throws ClassNotFoundException, SQLException {
        Response response = null;
            Class.forName(DRIVER);   // carregar o driver
           Connection comn =  DriverManager.getConnection(URL, USER, PASS);    
        PreparedStatement stmt =  comn.prepareStatement("select * from categoria order by nomeCategoria"); //PREPARA 
       ResultSet rs =  stmt.executeQuery(); // quando so vai consultar faz query
       
       List<Categoria> categorias = new ArrayList<Categoria>(); // lista que guarda categoria
       
       while(rs.next()){ //enquanto tiver proximo, pego os dados 
           Long id     = rs.getLong("idCategoria"); 
           String nome = rs.getString("nomeCategoria");
           String descricao = rs.getString("descCategoria");
           
           Categoria c = new Categoria(id,nome,descricao); //cria a categoria
           categorias.add(c); // adiciona na lista 
       }
       
       response = Response.ok(categorias).build();
        return response;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCategoria(@PathParam("id") Long id) {
        Response response = null;

        return response;
    }

    @POST // criar
    @Consumes("application/json") // vai consumi um Json
    public Response postCategoria(Categoria categoria) {
        Response response = null;

        return response;
    }

    @PUT //  atualiza 
    @Path("/{id}") // em um lugar especifico
    @Consumes("application/json") // este metodo consome um json
    public Response putCategoria(@PathParam("id") Long id, Categoria categoria) {
        Response response = null;

        return response;
    }

    @DELETE 
    @Path("/{id}")
    public Response deleteCategoria(@PathParam("id") Long id) {
        Response response = null;

        return response;
    }

}