/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DetalhesResource;

import Detalhes.Detalhe;
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
    public Response getDetalhes() throws ClassNotFoundException, SQLException {
        Response response = null;
        Class.forName(DRIVER);   // carregar o driver
        Connection comn =  DriverManager.getConnection(URL, USER, PASS);    
        PreparedStatement stmt =  comn.prepareStatement("select * from produto order by nomeProduto"); //PREPARA 
        ResultSet rs =  stmt.executeQuery(); // quando so vai consultar faz query
       
       List<Detalhe> detalhes = new ArrayList<Detalhe>(); // lista que guarda categoria
       
       while(rs.next()){ //enquanto tiver proximo, pego os dados 
           Long id     = rs.getLong("idCategoria"); 
           String nome = rs.getString("nomeCategoria");
           String descricao = rs.getString("descCategoria");
           Double preco = rs.getDouble("preco");
           Double desconto = rs.getDouble("desconto");
           
           Detalhe d = new Detalhe(id,nome,descricao,preco,desconto); //cria a categoria
           detalhes.add(d); // adiciona na lista 
       }
       
       response = Response.ok(detalhes).build();
        return response;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getDetalhes(@PathParam("id") Long id) {
        Response response = null;

        return response;
    }

    @POST // criar
    @Consumes("application/json") // vai consumi um Json
    public Response postDetalhes(Detalhe detalhe) {
        Response response = null;

        return response;
    }

    @PUT //  atualiza 
    @Path("/{id}") // em um lugar especifico
    @Consumes("application/json") // este metodo consome um json
    public Response putDetalhes(@PathParam("id") Long id, Detalhe detalhes) {
        Response response = null;

        return response;
    }

    @DELETE 
    @Path("/{id}")
    public Response deleteDetalhes(@PathParam("id") Long id) {
        Response response = null;

        return response;
    }

    
}
