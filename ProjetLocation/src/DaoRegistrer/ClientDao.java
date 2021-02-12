package DaoRegistrer;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import ModelRegistrer.Client;

public class ClientDao {
	
	public int registrerClient (Client client ) throws ClassNotFoundException {
		String INSERT_USERS_SQL ="INSERT INTO CLIENT "+
	"(nom,prenom,motdepasse,adresse,codePostal,ville) VALUES "+
				"(?,?,?,?,?,?);";
		
		/*String LOGIN_USERS_SQL ="INSERT INTO LOGIN "+
				"(nom,motdepasse) VALUES "+
							"(?,?);";*/
		
		
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/locationski","root","");
				
				
				//step 2:Create a statement using connection object
			   java.sql.PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			   //preparedStatement.setInt(1, 1);
			   preparedStatement.setString(1, client.getNom());
			   preparedStatement.setString(2, client.getPrenom());
			   preparedStatement.setString(3, client.getMotdepasse());
			   preparedStatement.setString(4, client.getAdresse());
			   preparedStatement.setString(5, client.getCodePostal());
			   preparedStatement.setString(6, client.getVille());
			   
			   System.out.println(preparedStatement);
			   //Step 3 : Execute ou update la requete
			   result = preparedStatement.executeUpdate();
			   
		   
			   
			   /*java.sql.PreparedStatement preparedStt = connection.prepareStatement(LOGIN_USERS_SQL)):{
				   preparedStt.setString(1, client.getNom());
				   preparedStt.setString(2, client.getMotdepasse());
				 
				   System.out.println(preparedStatement);
				   result = preparedStt.executeUpdate();
				   
			   }*/
			      
		}catch(SQLException e) {
			//sql exception
			e.printStackTrace();
		}
		return result;
		

					
				
	}	
	
	public int registrerLogin(Client client ) throws ClassNotFoundException {
		String INSERT_LOGIN_SQL ="INSERT INTO LOGIN "+
	"(nom,motdepasse) VALUES "+
				"(?,?);";
		
		/*String LOGIN_USERS_SQL ="INSERT INTO LOGIN "+
				"(nom,motdepasse) VALUES "+
							"(?,?);";*/
		
		
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/locationski","root","");
				
				
				//step 2:Create a statement using connection object
			   java.sql.PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGIN_SQL)){
			   //preparedStatement.setInt(1, 1);
			   preparedStatement.setString(1, client.getNom());
			   preparedStatement.setString(2, client.getMotdepasse());
			  
			   
			   System.out.println(preparedStatement);
			   //Step 3 : Execute ou update la requete
			   result = preparedStatement.executeUpdate();
			   
		   
			      
		}catch(SQLException e) {
			//sql exception
			e.printStackTrace();
		}
		return result;
		

					
				
	}	
	
	//verifier l'existence du client
	/*public Client VerifLoginClient(String nom , String prenom)throws ClassNotFoundException {
	
		Client c = new Client ();
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			java.sql.Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/locationski","root","");   
			java.sql.Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery(" SELECT * FROM Client  WHERE idClient= ? AND prenom = ? ");  
		
		     rs.updateString(1, nom);	
		     rs.updateString(2,prenom);
			
			while(rs.next()) {
				c.setNom(rs.getString(1));
				c.setPrenom(rs.getString(2));
				c.setAdresse(rs.getString(3));
				c.setCodePostal(rs.getString(4));
				c.setVille(rs.getString(5));
			} 
			    
			con.close();  
			
			}catch(Exception e){
			System.out.println(e);
			}
		return null;  
	}
*/
       
}	
	
