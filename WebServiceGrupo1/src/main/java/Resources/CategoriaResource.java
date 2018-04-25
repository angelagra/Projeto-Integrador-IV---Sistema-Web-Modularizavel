/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

/**
 *
 * @author leonardo.prodrigues6
 */
import Lista.Categoria;
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
public class CategoriaResource{

        //final significa que nao pode mudar, por ser uma constante, e sempre ser Maiuscula
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
    private static final String USER = "TSI";
    private static final String PASS = "SistemasInternet123";

    
      @GET
    @Produces("application/json")
    public Response getCategorias() {
        Response response = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from categoria order by nomeCategoria");
                ResultSet rs = stmt.executeQuery()) {
            List<Categoria> categorias = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("idCategoria");
                String nome = rs.getString("nomeCategoria");
                String descricao = rs.getString("descCategoria");

                Categoria categoria = new Categoria(null, nome, descricao);
                categorias.add(categoria);
            }
            response = Response.ok(categorias).build();
        } catch (ClassNotFoundException | SQLException ex) {
            response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ex == null ? "?" : ex.getMessage()).build();
        }

        return response;
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCategoria(@PathParam("id") Long id) {
        Response response = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from categoria where idCategoria = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nomeCategoria");
                    String descricao = rs.getString("descCategoria");

                    Categoria categoria = new Categoria(id, nome, descricao);
                    response = Response.ok(categoria).build();
                } else {
                    response = Response.status(Response.Status.NOT_FOUND).entity(id).build();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            response = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ex == null ? "?" : ex.getMessage()).build();
        }

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
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }
}