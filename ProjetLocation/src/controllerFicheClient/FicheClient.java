package controllerFicheClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

/**
 * Servlet implementation class FicheClient
 */
@WebServlet("/FicheClient")
public class FicheClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FicheClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{		
			
	        //chargement du pilote
	        Class.forName("com.mysql.jdbc.Driver");
	        //Creer la connection
	        Connection conn = DriverManager.getConnection
	                ("jdbc:mysql://localhost:3306/locationski", "root", "");
	        
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `client` WHERE idClient = ? ");
//	        !!!!!!!!!!!!!!!!!!!!!!  Remplacer "5" par ID Client session en bas !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	        ps.setString(1,(String) request.getSession().getAttribute("id"));
            ResultSet rs = ps.executeQuery();
            
     
            OutputStream out = response.getOutputStream();
        	String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"Report"+".txt\";");
	        response.setHeader(headerKey, headerValue);
	        
            
	        String text = "Fiche Client \n\n\n\n\n\n";
	  
            while(rs.next()){
            
            	text += " ID client : "+rs.getString("idClient")
            		+"\n\n Nom : "+rs.getString("nom")
            		+"\n\n Prenom : "+rs.getString("prenom")
            		+"\n\n Adresse : "+rs.getString("adresse")
            		+"\n\n Code Postal : "+rs.getString("codePostal")
            		+"\n\n Ville : "+rs.getString("ville");
            }	
            		
            	
		    StringBuffer sb = new StringBuffer(text);
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
		        
		        // obtains response's output stream
		    OutputStream outStream = response.getOutputStream();
		    byte[]outputByte = new byte[4096];//copy binary contect to output stream
		    while(in.read(outputByte, 0, 4096) != -1)
			{
				   out.write(outputByte, 0, 4096);
			}

			in.close();
			out.flush();
		    outStream.close(); 
		    
		    rs.close();
		    ps.close();
		    conn.close();
			        				        
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
