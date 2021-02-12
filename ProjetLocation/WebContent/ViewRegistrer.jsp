<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
</head>
<body>
<div class="progress-bar" data-label = "chargement..."></div> 

<form action="<%= request.getContextPath() %>/register" method="post" name="myForm">
<div id="content">
<div class="container-register"><br>
    <div id="message"/>
	<table>
	<h3><strong>INSCRIPTION</strong></h3>
	<tr>
	<td> Nom:</td>
	<td><input type="text"  class= "form-control" name="nom" id="name" placeholder="nom" /></td>
	</tr>
	<tr>
	<td> Prenom:</td>
	<td><input type="text"  class= "form-control" name="prenom" id="fn"  placeholder="prenom"/></td>
	</tr>
	<td> MotdePasse:</td>
	<td><input type="password"  class= "form-control" name="motdepasse" id="mdp"  placeholder="motdepasse"/></td>
	</tr>
	<tr>
	<td> Adresse:</td>
	<td><input type="text" class= "form-control" name="adresse"  id="adress" placeholder="adresse"/></td>
	</tr>
	<tr>
	<td> CodePostal:</td>
	<td><input type="text" class= "form-control" name="codePostal" id="cdp"  placeholder="codepostal"/></td>
	</tr>
	<tr>
	<td> Ville:</td>
	<td><input type="text" class= "form-control"  name="ville"  id="ville" placeholder="ville" /></td>
	</tr>
	</table><br>
	<button type="button"  class="btn btn-primary" name="envoyer" id="envoyer" onclick="valider()" >S'inscrire</button>
	<br>
	  <p>Vous êtes inscrit ? Connectez-vous <a href="http://localhost:8080/ProjetLocation/ViewLogin.jsp">ici !</a></p>
</div>
<div>	
</form>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="script.js"></script>
<script>
//load bar
$(document).ready(function (){
	$(".progress-bar").fadeOut(5000 , function(){
		$("#content").fadeIn(1000);
	});
});

//verif not empty
 function valider() {
	  if(document.myForm.nom.value != "" && document.myForm.prenom.value != ""  && document.myForm.adresse.value != ""  && document.myForm.codePostal.value != ""  
		  && document.myForm.ville.value != "" ) {
	    document.myForm.submit();
	  }
	  else {
	    // sinon on affiche un message
	    alert("Veuillez remplir le(s) champ(s) manquant(s) ! ");
	  }
	};

 </script>   
   
</body>
</html>