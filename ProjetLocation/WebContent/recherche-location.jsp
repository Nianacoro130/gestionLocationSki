<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				<%if(request.getParameter("date-debut") != null && request.getParameter("date-fin") != null){ 
					out.print("<a href=\"ListeLocation?debut="+request.getParameter("date-debut")+"&fin="+request.getParameter("date-fin")+"\"><button>Télécharger liste</button></a>");
				}else{
					out.print("<a href=\"ListeLocation\"><button>Télécharger liste</button></a>");
				}%>
			</div>
			<div class="partie-droite">
				<form action="" method="GET">
					<label for="date-debut">Date début :
						<input name="date-debut" type="date" >
					</label>
					<label for="date-fin">Date fin :
						<input name="date-fin" type="date" >
					</label>
					<input type="submit" value="Rechercher">
				</form>
				<div class="liste-location">
					<table>
					 <tr>
					  <th>Id Location</th>
					  <th>Id Client</th>
					  <th>Date Location</th>
					  <th>Etat</th>
					 </tr>
					
					<%
						try{
					        //chargement du pilote
					        Class.forName("com.mysql.jdbc.Driver");
					        //Creer la connection
					        Connection conn = DriverManager.getConnection
					                ("jdbc:mysql://localhost:3306/locationski", "root", "");
					        
					        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `locations` ");
			                
				                 //Préparer la requette 
				            if(request.getParameter("date-debut") != null && request.getParameter("date-fin") != null){
				            	ps = conn.prepareStatement("SELECT * FROM `locations` WHERE dateLocation BETWEEN ? AND ? ");
				            	
				            	
				            	/* SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
				                Date dateDebut = new Date(request.getParameter("date-debut")); */
				                
				            	
				            	ps.setString(1, request.getParameter("date-debut"));
				              	ps.setString(2, request.getParameter("date-fin")); 
				            } 
				             
				           
				            ResultSet rs = ps.executeQuery();
				            
				            ResultSetMetaData rsmd=rs.getMetaData();
				            
				           
				            while(rs.next()){
				                System.out.println(rs.getString("idLocations")+" , "+ rs.getString("client"));
				          
				            	out.print("<tr>"
				            	+"<td>"+rs.getString("idLocations")+"</td>"
				            	+"<td>"+rs.getString("client")+"</td>"
				            	+"<td>"+rs.getString("dateLocation")+"</td>"
				            	+"<td>"+rs.getString("etat")+"</td>"
				            	+"</tr>");
					            
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