<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> 
<%@include file="navigation.jsp" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" rel="stylesheet">
<title>Location</title>
</head>
<body>
	<main>
	<% out.println("<p> Bonjour, " +(request.getSession().getAttribute("nom"))+"&nbsp"+(request.getSession().getAttribute("prenom"))+", vous êtes connecté !</p>"); %>
		<h1>Location</h1>
		<div>
			<%
				try{
					 
					
					
					        //chargement du pilote
					        Class.forName("com.mysql.jdbc.Driver");
					        //Creer la connection
					        Connection conn = DriverManager.getConnection
					                ("jdbc:mysql://localhost:3306/locationski", "root", "");
					        
					        PreparedStatement ps =conn.prepareStatement("SELECT * FROM `articles` WHERE idArticle = ? ");
					        ps.setString(1, request.getParameter("article"));
			                
				            ResultSet rs = ps.executeQuery();
				            
				            ResultSetMetaData rsmd=rs.getMetaData();
				            
				            
				           
				            while(rs.next()){
				                System.out.println(rs.getString("libelle")+" , "+ rs.getString("libelle"));
				          
				            	out.print("<div class=\"article\">"
				            	+"<img width=\"100px\" src=\"https://image.freepik.com/free-icon/skiing_318-9893.jpg\">"
				            	+"<div>Libelle : "+rs.getString("libelle")+"</div>"
				            	+"<div>Prix journalier : "+rs.getString("prixJournalier")+"</div>"
				            	+"<div class=\"cont-form-louer\">"
				            	+	"<form action=\"http://localhost:8080/ProjetLocation/Location\" method=\"POST\">"
				            	+		"<label>Date debut"
				            	+			"<input name=\"date-debut\" type=\"date\" required>"
				            	+		"</label>"
				            	+		"<label>Date fin"
				            	+			"<input name=\"date-fin\" type=\"date\" required>"
				            	+		"</label>"
				            	+			"<input name=\"article\" type=\"hidden\" value=\""+request.getParameter("article")+"\" required>"
				            	+		"<input type=\"submit\" value=\"Louer\">"
				            	+	"</form>"
				      			+"</div>");
				            	
				            }	
				            
				                         
				            rs.close();
				            ps.close();
				            conn.close();
					        				        
					       }catch(Exception e){
					           System.out.println(e);
					       }
						
				%>
			
		</div>
	</main>

</body>
</html>