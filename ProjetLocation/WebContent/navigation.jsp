<nav>
	<div class="nav-left">
		<a href="http://localhost:8080/ProjetLocation/locations-client.jsp">Liste locations</a>
		<a href="http://localhost:8080/ProjetLocation/liste-articles.jsp">Articles</a>
		<a href="http://localhost:8080/ProjetLocation/recherche-location.jsp">Recherche Location</a>
	</div>
	<%if(request.getSession().getAttribute("id") == null){ %>
	<div>
		<a href="http://localhost:8080/ProjetLocation/ViewLogin.jsp">Connexion</a>
	</div>
	<%}else{%>
	<div>
		<a href="http://localhost:8080/ProjetLocation/ViewLogin.jsp"> Déconnexion</a>
		
	</div>
	<%} %>
	
</nav>