<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>     
 <%@ page import="modelLogin.Login" %> 
 <%@include file="navigation.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" rel="stylesheet">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>
	<% out.println("<p> Bonjour, " +(request.getSession().getAttribute("nom"))+"&nbsp"+(request.getSession().getAttribute("prenom"))+", vous êtes connecté !</p>"); %>

		<h1>Liste Articles</h1>
		
		<%
		
		
				try{
					        //chargement du pilote
					        Class.forName("com.mysql.jdbc.Driver");
					        //Creer la connection
					        Connection conn = DriverManager.getConnection
					                ("jdbc:mysql://localhost:3306/locationski", "root", "");
					        
					        PreparedStatement ps =conn.prepareStatement("SELECT * FROM `articles` ");
			                
				            ResultSet rs = ps.executeQuery();
				            
				            ResultSetMetaData rsmd=rs.getMetaData();
				            
				           
				            while(rs.next()){
				                System.out.println(rs.getString("libelle")+" , "+ rs.getString("libelle"));
				          
				            	out.print("<div class=\"article\">"
				            	+"<img width=\"100px\" src=\"https://image.freepik.com/free-icon/skiing_318-9893.jpg\">"
				            	+"<div>Libelle : "+rs.getString("libelle")+"</div>"
				            	+"<div>Prix journalier : "+rs.getString("prixJournalier")+"</div>"
				            	+"<div><a href=\"location.jsp?article="+rs.getString("idArticle")+"\"><button>Louer</button></a></div>"
				      			+"</div>");
				            }	
				            
				 
				            rs.close();
				            ps.close();
				            conn.close();
					        				        
					       }catch(Exception e){
					           System.out.println(e);
					       }
						
				%>

	
	</main>
</body>
</html>