package LoginDao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import modelLogin.Login;

public class LoginDao {
	
	public boolean valider (Login login) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/locationski","root","");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("select * from login where nom = ? and motdepasse= ? ")) {
            preparedStatement.setString(1, login.getNom());
            preparedStatement.setString(2, login.getMotdepasse());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    
  //verifier l'existence du client
  /*	public Login VerifLoginClient(String nom , String motdepasse)throws ClassNotFoundException {
  	
  		Login c = new Login ();
  		
  		try{  
  			Class.forName("com.mysql.jdbc.Driver");  
  			java.sql.Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/locationski","root","");   
  			java.sql.Statement stmt=con.createStatement(); 
  			ResultSet rs=stmt.executeQuery(" SELECT * FROM Client  WHERE idClient= ? AND password = ? ");  
  		
  			 
  		     rs.updateString(1, nom);	
  		     rs.updateString(2,motdepasse);
  			
  			while(rs.next()) {
  				c.setNom(rs.getString(1));;
  				c.setMotdepasse(rs.getString(2));
  			} 
  			    :
  			con.close();  
  			
  			}catch(Exception e){
  			System.out.println(e);
  			}
  		return null;  
  	}
     
    
*/
}
