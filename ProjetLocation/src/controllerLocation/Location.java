package controllerLocation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import LoginDao.LoginDao;
import modelLogin.Login;

import java.sql.*;

/**
 * Servlet implementation class Location
 */
@WebServlet("/Location")
public class Location extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Location() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("article"));
		HttpSession session = request.getSession(); 
		session.getAttribute("nom");
		
	      
		 
		
		try{
			
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
			//System.out.println(request.getSession().getAttribute("nom"));
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			
			
            //chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");
            //Creer la connection
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/locationski", "root", "");
            
              
           
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! page Location");
            PreparedStatement ps =conn.prepareStatement ("SELECT * FROM locations WHERE Client = ?");
            ps.setString(1,(String) request.getSession().getAttribute("id"));
            
            String location;
            ResultSet rs = ps.executeQuery();
            boolean result = rs.next();
           
            location = rs.getString("idLocations");
            System.out.println(location);
               ps.close();
            
            if(!result) {
            	
              PreparedStatement ps1 =conn.prepareStatement ("INSERT INTO locations (Client, dateLocation, etat)"
     			 + "VALUES (?,?,?)");
      
		      ps1.setString(1,(String) request.getSession().getAttribute("id"));
		      ps1.setString(2, request.getParameter("date-debut"));
		      ps1.setString(3, "ok");
		      
		      ps1.executeUpdate();
		      ps1.close();
		      
		      PreparedStatement ps3 = conn.prepareStatement ("SELECT * FROM locations ORDER BY idLocations DESC LIMIT 1");
		        
		      ResultSet rs3 = ps3.executeQuery();
		      
		      System.out.println("rfre");
		      System.out.println(Integer.parseInt(rs3.getString("idLocations")) + 1);
		      int loc = Integer.parseInt(rs3.getString("idLocations")) + 1;
		      location = String.valueOf(loc);
            		
            }
         
                       
            
            PreparedStatement ps2 =conn.prepareStatement ("INSERT INTO lignesloc (location, article, debut, fin)"
            		 + "VALUES (?,?,?,?)");
            
            ps2.setString(1, location);
            ps2.setString(2, request.getParameter("article"));
            ps2.setString(3, request.getParameter("date-debut"));
            ps2.setString(4, request.getParameter("date-fin"));
            
            ps2.executeUpdate();
            ps2.close();
            conn.close();
                               
           }catch(Exception e){
               System.out.println(e);
           }

		
			request.getRequestDispatcher("liste-articles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("!!!!!!!! page location !!!!");
		System.out.println(request.getParameter("article"));
		HttpSession session = request.getSession(); 
		session.getAttribute("nom");
		
	      
		 
		
		try{
			
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
			//System.out.println(request.getSession().getAttribute("nom"));
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			
			
            //chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");
            //Creer la connection
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/locationski", "root", "");
            
              
           
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! page Lcation");
            PreparedStatement ps =conn.prepareStatement ("SELECT * FROM locations WHERE Client = ?");
            ps.setString(1,(String) request.getSession().getAttribute("id"));
            
            String location;
            ResultSet rs = ps.executeQuery();
            boolean result = rs.next();
           
            location = rs.getString("idLocations");
            System.out.println(location);
               ps.close();
            
            if(!result) {
            	
              PreparedStatement ps1 =conn.prepareStatement ("INSERT INTO locations (Client, dateLocation, etat)"
     			 + "VALUES (?,?,?)");
      
		      ps1.setString(1,(String) request.getSession().getAttribute("id"));
		      ps1.setString(2, request.getParameter("date-debut"));
		      ps1.setString(3, "ok");
		      
		      ps1.executeUpdate();
		      ps1.close();
		      
		      PreparedStatement ps3 = conn.prepareStatement ("SELECT * FROM locations ORDER BY idLocations DESC LIMIT 1");
		        
		      ResultSet rs3 = ps3.executeQuery();
		      
		      System.out.println("rfre");
		      System.out.println(Integer.parseInt(rs3.getString("idLocations")) + 1);
		      int loc = Integer.parseInt(rs3.getString("idLocations")) + 1;
		      location = String.valueOf(loc);
            		
            }
         
                       
            
            PreparedStatement ps2 =conn.prepareStatement ("INSERT INTO lignesloc (location, article, debut, fin)"
            		 + "VALUES (?,?,?,?)");
            
            ps2.setString(1, location);
            ps2.setString(2, request.getParameter("article"));
            ps2.setString(3, request.getParameter("date-debut"));
            ps2.setString(4, request.getParameter("date-fin"));
            
            ps2.executeUpdate();
            ps2.close();
            conn.close();
                               
           }catch(Exception e){
               System.out.println(e);
           }

		
			request.getRequestDispatcher("liste-articles.jsp").forward(request, response);
	}

		 	
	
	
}
