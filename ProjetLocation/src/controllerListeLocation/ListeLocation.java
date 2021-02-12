package controllerListeLocation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class ListeLocation
 */
@WebServlet("/ListeLocation")
public class ListeLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			try {
		        //chargement du pilote
		        Class.forName("com.mysql.jdbc.Driver");
		        //Creer la connection
		        Connection conn = DriverManager.getConnection
		                ("jdbc:mysql://localhost:3306/locationski", "root", "");
		        
		        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `locations` ");

	                 //Préparer la requette 
	            if(request.getParameter("debut") != null && request.getParameter("fin") != null){
	            	ps = conn.prepareStatement("SELECT * FROM `locations` WHERE dateLocation BETWEEN ? AND ? ");
	            	
	            	
	            	/* SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
	                Date dateDebut = new Date(request.getParameter("date-debut")); */
       	
	            	ps.setString(1, request.getParameter("debut"));
	              	ps.setString(2, request.getParameter("fin")); 
	            } 

	           
	            ResultSet rs = ps.executeQuery();
	        
	            
	            OutputStream out = response.getOutputStream();
	        	String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"Report"+".txt\";");
		        response.setHeader(headerKey, headerValue);
	            
		        String text = "Liste Location \t\t  \n\n\n\n\n\n";
		        
		        if(request.getParameter("debut") != null && request.getParameter("fin") != null) {
		        	
		        	text += "Du "+request.getParameter("debut")+" Au "+request.getParameter("fin")+"\n\n\n\n";
		        }
	           
	            while(rs.next()){
	                System.out.println(rs.getString("idLocations")+" , "+ rs.getString("client"));
	          
	                text +=
	            	"ID location : "+rs.getString("idLocations")+" | "
	            	+"ID Client : "+rs.getString("client")+" | "
	            	+"Date Location : "+rs.getString("dateLocation")+" | "
	            	+"Etat : "+rs.getString("etat")+"\n\n";
		            
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
			
		
			request.getRequestDispatcher("recherche-location.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
