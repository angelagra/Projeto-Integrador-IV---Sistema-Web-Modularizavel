/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.webservicegrupo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author dgaraujo
 */
@Path("/Login")
public class LoginResource {

    //final significa que nao pode mudar, por ser uma constante, e sempre ser Maiuscula
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://br-note02-spo:1433;databaseName=hippo;user=dgaraujo@br-note02-spo;password=At3nto01";
    private static final String USER = "hippo";
    private static final String PASS = "hippo";

    @GET
    @Produces("application/json")
    public Response getUsuarios() throws ClassNotFoundException, SQLException {
        Response response = null;
        Class.forName(DRIVER);   // carregar o driver
        Connection comn =  DriverManager.getConnection(URL, USER, PASS);    
        PreparedStatement stmt =  comn.prepareStatement("select * from Usuario  where usuarioAtivo = 1 order by loginUsuario"); //PREPARA 
       ResultSet rs =  stmt.executeQuery(); // quando so vai consultar faz query
       
       List<Login> Usuarios = new ArrayList<Login>(); // lista que guarda categoria
       
       while(rs.next()){ //enquanto tiver proximo, pego os dados 
           Long id     = rs.getLong("idUsuario"); 
           String loginUsuario = rs.getString("loginUsuario");
           String NomeUsuario = rs.getString("NomeUsuario");
           String senhaUsuario = rs.getString("senhaUsuario");
           
           Login c = new Login(id,loginUsuario,NomeUsuario,senhaUsuario); //cria a categoria
           Usuarios.add(c); // adiciona na lista 
       }
       
       response = Response.ok(Usuarios).build();
        return response;
    }

    @POST // criar
    @Consumes("application/json") // vai consumi um Json
    public Response postLogins(Login Login) {
        Response response = null;

        return response;
    }

    @GET //  atualiza 
    @Path("/{loginUsuario}") // em um lugar especifico
    @Consumes("application/json") // este metodo consome um json
    public Response putLogins(@PathParam("loginUsuario") String loginUsuario, Login login) {
        Response response = null;

        String sql = "select * from Usuario where loginUsuario = ? ";
        
        try(Connection comn = getConnection(); PreparedStatement stmt =  comn.prepareStatement(sql)){
            
        }
        
        return response;
    }

}
