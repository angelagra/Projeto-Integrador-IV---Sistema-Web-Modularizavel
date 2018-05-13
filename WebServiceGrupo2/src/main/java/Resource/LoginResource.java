/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resource;

import Login.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author dgaraujo
 */
@Path("/login")
public class LoginResource {
    
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
        private static final String USER = "TSI";
        private static final String PASS = "SistemasInternet123";


    @Context
    private UriInfo context;
    private String emailCliente;
    private String senhaCliente;
    

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of Resource.LoginResource
     * @param password
     * @return an instance of java.lang.String
     */
  

    @POST
    //@Path("/{emailCliente},{senhaCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(Login login) throws SQLException, ClassNotFoundException {
  
        
        Response response = null;
        Class.forName(DRIVER);   // carregar o driver
        Connection comn =  DriverManager.getConnection(URL, USER, PASS);    

        String sql = "select emailCliente,senhaCliente from Cliente where emailCliente = ? and senhaCliente = ?";
        
        try(PreparedStatement stmt = comn.prepareStatement(sql)){
            
            emailCliente = "daniel@senac.br";
            senhaCliente = "123mudar";
            
            
           stmt.setString(1,emailCliente);
           stmt.setString(2,senhaCliente);// colocando um valor
           
            try(ResultSet rs = stmt.executeQuery()){

                if(rs.next()){
                     String usuario = rs.getString("emailCliente");
                     String senha = rs.getString("senhaCliente");
               
                     Login c = new Login(usuario,senha); //cria a Usuario

                     //response = Response.ok(c).build();
                     
                     response = Response.ok(c)
                             .entity("true")
                             .type(MediaType.APPLICATION_JSON).build();
                    		
                }else{
                    //não tem
                    response = Response.status(Response.Status.UNAUTHORIZED)
                            .entity("false")
                            .type(MediaType.APPLICATION_JSON).build();
                }
           }
        }

       return response;
    }
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER); // carregar o driver
        return DriverManager.getConnection(URL, USER, PASS);
    }        

     /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     */

   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   public void putJson(String content) {
        throw new UnsupportedOperationException();
   }
}
