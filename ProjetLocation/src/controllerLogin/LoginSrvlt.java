package controllerLogin;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import LoginDao.LoginDao;
import modelLogin.Login;

/**
 * Servlet implementation class LoginSrvlt
 */
@WebServlet("/login")
public class LoginSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private LoginDao loginDao;
       
	 
	 
	 public void init() {
		 loginDao = new LoginDao();
	 }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSrvlt() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		try{
            //chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println(request.getParameter("nom"));
            System.out.println(request.getParameter("motdepasse"));
            //Creer la connection
            Connection conn = (Connection) DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/locationski", "root", "");

            PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM client WHERE nom = ? AND motdepasse = ? ");
            ps.setString(1, request.getParameter("nom"));
            ps.setString(2, request.getParameter("motdepasse"));

            ResultSet rs = ps.executeQuery();



           if(rs.next()) {
                   System.out.println("Successs");
                    HttpSession session = request.getSession(); 
      			    session.setAttribute("id",rs.getString("idClient"));
      			    session.setAttribute("nom", rs.getString("nom"));
      			    session.setAttribute("prenom", rs.getString("prenom"));
                   request.getRequestDispatcher("/liste-articles.jsp").forward(request,response);


           }else {
               System.out.println("Errrrrrrrrrrrrreur");
               request.setAttribute("message","Client non trouvé, veuillez réessayer ou vous inscrire!");
			    request.getRequestDispatcher("/ViewLogin.jsp").forward(request, response);
           }


 
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
  	
	    }

		
}
		
	