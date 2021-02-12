<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
</head>
</head>
<body>
<div class="progress-bar" data-label = "chargement..."></div> 

<div>${message}</div>
  <form action="<%=request.getContextPath()%>/login" method="get" name="monform">
  <div id="content">
  <div class="container-login">
    <h3>CONNEXION</h3>
   <table>
    <tr>
     <td>Nom:</td>
     <td><input type="text" class="form-control" name="nom" /></td>
    </tr>
    <tr>
     <td>Mot de Passe :</td>
     <td><input type="password"  class="form-control" name="motdepasse" /></td>
    </tr>
   </table>
   <button type="submit"  class="btn btn-primary"  onclick="confirmer()" >Se connecter </button>
</div>
<p>Vous n'êtes pas inscrit ? Inscrivez-vous <a href="http://localhost:8080/ProjetLocation/ViewRegistrer.jsp">ici !</a></p>
</div>
</form>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="script.js"></script>
<script>
$(document).ready(function (){
	$(".progress-bar").fadeOut(5000 , function(){
		$("#content").fadeIn(1000);
	});
});


function confirmer() {
	  if(document.monform.nom.value != "" && document.monform.password.value  ) {
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