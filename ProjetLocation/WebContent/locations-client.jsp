<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="modelLogin.Login" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>     
<%@include file="navigation.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<% out.println("<p> Bonjour, " +(request.getSession().getAttribute("nom"))+"&nbsp"+(request.getSession().getAttribute("prenom"))+", vous êtes connecté !</p>"); %>
	<main>
		<h1>Liste Locations</h1>
		<div class="partie-principale">
			<div class="partie-gauche">
				<a href="liste-articles.jsp"><button>Ajouter une location</button></a>
				<a href="ListeLocation"><button>Telecharger la liste</button></a>
				<a href="FactureLocation"><button>Facture</button></a>
				
			</div>
			<div class="partie-droite">
				<!-- form action="" method="GET">
					<label for="date-debut">Date début :
						<input name="date-debut" type="date">
					</label>
					<label for="date-fin">Date fin :
						<input name="date-fin" type="date">
					</label>
					<input type="submit" value="Rechercher">
				</form--> 
				<div class="liste-location">
					<table>
					 <tr>
					  <th>Article</th>
					  <th>Début</th>
					  <th>Fin</th>
					  <th></th>
					 </tr>
					
					<%
						try{
					        //chargement du pilote
					        Class.forName("com.mysql.jdbc.Driver");
					        //Creer la connection
					        Connection conn = DriverManager.getConnection
					                ("jdbc:mysql://localhost:3306/locationski", "root", "");
					        
					        PreparedStatement ps = conn.prepareStatement("SELECT * FROM lignesloc WHERE location = (SELECT idLocations FROM locations WHERE client = ?) ");
					        ps.setString(1,(String) request.getSession().getAttribute("id"));
					          
				           
				            ResultSet rs = ps.executeQuery();
				            
				            ResultSetMetaData rsmd = rs.getMetaData();
				           
				            while(rs.next()){
				            	String article = "";
				                System.out.println(rs.getString("article")+" , "+ rs.getString("debut"));
				              
				                PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM articles WHERE idArticle = ?");
						        ps1.setString(1, "1");
						        ResultSet rs1 = ps1.executeQuery();
						        while(rs1.next()){
						        	article = rs1.getString("libelle");
						        }

				            	out.print("<tr>"
				            	+"<td>"+article+"</td>"
				            	+"<td>"+rs.getString("debut")+"</td>"
				            	+"<td>"+rs.getString("fin")+"</td>"
				            	+"<td><a href=\"Retour?location="+rs.getString("ligne")+"\"><button>Retour</button></a></td>"
				            	+"</tr>");
				            	
				            	 rs1.close();
						         ps1.close();
				            }	
				            
				            rs.close();
				            ps.close();
				            conn.close();
					        				        
					       }catch(Exception e){
					           System.out.println(e);
					        }
						
						%>
						</table>
				</div>
			</div>
		</div>
	</main>




</body>
</html>