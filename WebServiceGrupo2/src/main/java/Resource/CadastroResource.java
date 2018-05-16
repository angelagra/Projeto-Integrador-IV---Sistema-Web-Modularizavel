/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resource;

import Cadastro.Cadastro;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    @Consumes(MediaType.APPLICATION_JSON) // vai consumi um Json
    @Produces(MediaType.APPLICATION_JSON)
    public Response postcadastro(Cadastro cadastro) throws SQLException, ClassNotFoundException {
    
        Response response = null; 
     
        Class.forName(DRIVER);   // carregar o driver
        Connection comn =  DriverManager.getConnection(URL, USER, PASS);    

        String sql = "INSERT\n" +
                        "INTO cliente (nomeCompletoCliente, emailCliente,\n" +
                        "senhaCliente, CPFCliente, celularCliente,\n" +
                        "telComercialCliente, telResidencialCliente,\n" +
                        "dtNascCliente, recebeNewsLetter)\n" +
                        "  VALUES (?, ?, ?,?, ?, ?, ?, (CONVERT(varchar(10), ?, 105)), ?);";
        
        try(PreparedStatement stmt = comn.prepareStatement(sql)){
           stmt.setString(1,cadastro.getNomeCompletoCliente());
           stmt.setString(2,cadastro.getEmailCliente());
           stmt.setString(3,cadastro.getSenhaCliente());
           stmt.setString(4,cadastro.getCpfCliente());
           stmt.setString(5,cadastro.getCelularCliente());
           stmt.setString(6,cadastro.getTelComercialCliente());
           stmt.setString(7,cadastro.getTelResidencialCliente());
           //stmt.setDate(8,new java.sql.Date(cadastro.getDtNasCliente().getTime()));
                 
          java.util.Date today = new java.util.Date();
          java.sql.Date sqlToday = new java.sql.Date(cadastro.getDtNasCliente());
          
          stmt.setDate(8,date.valueOf(cadastro.getDtNasCliente()));
          
          ps.setString(8,sqlToday); 
          
           stmt.setString(9,cadastro.getRecebeNewsLetter());

           int n = stmt.executeUpdate();
           stmt.close();
           
        }
          return response;
     
    }

    private Date getCurrentJavaSqlDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
