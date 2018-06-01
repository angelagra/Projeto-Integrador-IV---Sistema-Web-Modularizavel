/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagem;

import Lista.Produtos;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

@WebServlet(name = "GetImagem", urlPatterns = {"/GetImagem"})
public class GetImagem extends HttpServlet {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://hippo-pi.database.windows.net;database=hippo";
    private static final String USER = "TSI";
    private static final String PASS = "SistemasInternet123";
    
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         URL url = null;

        String id = request.getParameter("id");
        Integer width = Integer.parseInt(request.getParameter("w"));
        
        Long lId = new Long (id);
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("select imagem from Produto where idProduto  = ?");

            stmt.setLong(1, lId.longValue());
            ResultSet rs = stmt.executeQuery();
            
             while(rs.next()){
                 InputStream in = rs.getBinaryStream("imagem");
                 response.setHeader("contentType", "image/jpeg");
                 OutputStream out = response.getOutputStream();
                 BufferedImage data= resize(in, width);
                 ImageIO.write (data, "jpg", out);
                /*
                // Copy the contents of the file to the output stream
                 byte[] buf = new byte[1024];
                 int count = 0;
                 while ((count = in.read(buf)) >= 0) {
                   out.write(buf, 0, count);
                }*/
               
                out.close();
                in.close();
                 
            }
            
        } catch (Exception ex) {

        }
        
        
    }

    public BufferedImage resize (InputStream imagem, int width) {
         
         BufferedImage dimg = null;
        try {
            BufferedImage originalImage = ImageIO.read(imagem);

            int w = originalImage.getWidth();
            int h = originalImage.getHeight();

            float razao = (float)w/ (float)h;

            int height = (int) (width / razao);


            dimg = new BufferedImage(width, height, originalImage.getType());
            Graphics2D g = dimg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, width, height, 0, 0, w, h, null);
            g.dispose();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            return dimg;     
    }
}
