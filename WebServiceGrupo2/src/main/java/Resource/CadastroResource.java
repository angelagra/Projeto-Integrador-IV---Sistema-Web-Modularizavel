/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resource;

import Cadastro.Cadastro;
import java.net.URI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author dgaraujo
 */
@Path("/cadastro")
public class CadastroResource {
    
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
        private static final String USER = "TSI";
        private static final String PASS = "SistemasInternet123";

    @Context
    private UriInfo context;
    private String nomeCompletoCliente;
    private String emailCliente;
    private String senhaCliente;
    private String CPFCliente;
    private String celularCliente;
    private String telComercialCliente;
    private String telResidencialCliente;
    private Date dtNascCliente;
    private String recebeNewsLetter;

    /**
     * Creates a new instance of CadastroResource
     */
    public CadastroResource() {
    }

    @POST
    //@Path("/{nomeCompletoCliente},{emailCliente},{senhaCliente},{CPFCliente},{celularCliente},{telComercialCliente},{telResidencialCliente},{dtNascCliente},{recebeNewsLetter}")
    @Consumes(MediaType.APPLICATION_JSON) // vai consumi um Json
    public Response postcadastro(Cadastro cadastro) throws SQLException, ClassNotFoundException {
    
        Response response = null; 
     
        Class.forName(DRIVER);   // carregar o driver
        Connection comn =  DriverManager.getConnection(URL, USER, PASS);    

        String sql = "INSERT\n" +
                        "INTO cliente (nomeCompletoCliente, emailCliente,\n" +
                        "senhaCliente, CPFCliente, celularCliente,\n" +
                        "telComercialCliente, telResidencialCliente,\n" +
                        "dtNascCliente, recebeNewsLetter)\n" +
                        "  VALUES (?, ?, ?,?, ?, NULL, NULL, (CONVERT(varchar(10), ?, 105)), 1);";
        
        try(PreparedStatement stmt = comn.prepareStatement(sql)){
           stmt.setString(1,nomeCompletoCliente);
           stmt.setString(2,emailCliente);
           stmt.setString(3,senhaCliente);
           stmt.setString(4,CPFCliente);
           stmt.setString(5,celularCliente);
           stmt.setString(6,telComercialCliente);
           stmt.setString(7,telResidencialCliente);
           stmt.setDate(8,new java.sql.Date(dtNascCliente.getTime()));
           stmt.setString(9,recebeNewsLetter);

           int n = stmt.executeUpdate();
           
           // retorna qual é o endereço id
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next()){
                Long id = rs.getLong(1);
                //cadastro.setId(id);
                response = Response.created(URI.create("cadastro/" +id)).build();
            }else{
                response = Response.status(Response.Status.CONFLICT).build();
            }
           
        }

       return response;
     
    }
    
    /**
     * Retrieves representation of an instance of Resource.CadastroResource
     * @return an instance of java.lang.String
     */
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    
    }
    */
}
