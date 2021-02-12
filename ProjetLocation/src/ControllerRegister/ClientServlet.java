package ControllerRegister;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoRegistrer.ClientDao;
import ModelRegistrer.Client;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/register")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDao clientDao = new ClientDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewRegistrer.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation des parametres envoyés depuis la page registrer
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String motdepasse = request.getParameter("motdepasse");
		String adresse = request.getParameter("adresse");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		Client client = new Client();
		
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setMotdepasse(motdepasse);
		client.setAdresse(adresse);
		client.setCodePostal(codePostal);
		client.setVille(ville);
		
		try {
			clientDao.registrerClient(client);
			clientDao.registrerLogin(client);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/detailsRegister.jsp");
		dispatcher.forward(request, response);
	}

}