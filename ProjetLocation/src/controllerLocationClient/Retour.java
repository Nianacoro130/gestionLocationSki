package controllerLocationClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelLogin.Login;

import java.sql.*;
/**
 * Servlet implementation class Retour
 */
@WebServlet("/Retour")
public class Retour extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Retour() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// TODO Auto-generated method stub
		//System.out.println("yooooooooooooo");
		
		
		try{
            //chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");
            //Creer la connection
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/locationski", "root", "");
         
            
            if(request.getParameter("location") != null) {
            	
              PreparedStatement ps = conn.prepareStatement ("DELETE FROM `lignesloc` WHERE ligne = ?");
      
		      ps.setString(1, request.getParameter("location"));
		      
		      ps.executeUpdate();
		      ps.close();
		
            }
         
                               
           }catch(Exception e){
               System.out.println(e);
           }

		
			request.getRequestDispatcher("locations-client.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 	
	}

}
