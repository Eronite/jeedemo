<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Order</title>
<link type="text/css" rel="stylesheet" href="inc/style.css"/>
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<div>
		<form method="post" action="<c:url value="/OrderCreation"/>">
			<%-- placer le client accessible via l'EL ${ commande.client }
			dans une variable "client" de portée request, pour que le code du fragment fonctionne à la fois
			depuis le formulaire de création d'un client et depuis celui de création d'une commande --%>
            <c:set var="client" value="${ order.client }" scope="request" />
            
			<fieldset>
				<legend>Informations client</legend>
				<%-- if clients map (in session) is not empty, display radio button --%>
                    <c:if test="${ !empty sessionScope.clients }">
                        <label for="choiceNewClient">Nouveau client ?</label>
                        <input type="radio" id="choiceNewClient" name="choiceNewClient" value="newClient" checked /> Oui
                        <input type="radio" id="choiceNewClient" name="choiceNewClient" value="existingClient" /> Non
                        <br/><br />
                    </c:if>
                    
                    <c:set var="client" value="${ order.client }" scope="request" />
                    <div id="newClient">
                        <c:import url="/inc/inc_client_form.jsp" />
                    </div>
                    
                    <%-- if clients map (in session) is not empty, display dropdown list --%>
                    <c:if test="${ !empty sessionScope.clients }">
                    <div id="existingClient">
                        <select name="listClients" id="listClients">
                            <option value="">Choisissez un client...</option>
                            <%-- iterate over clientz map --%>
                            <c:forEach items="${ sessionScope.clients }" var="mapClients">
                            <%--  L'expression EL ${mapClients.value} permet de cibler l'objet Client stocké en tant que valeur dans la Map, 
                                  et on cible ensuite simplement ses propriétés nom et prenom comme on le ferait avec n'importe quel bean. --%>
                            <option value="${ mapClients.value.name }">${ mapClients.value.firstName } ${ mapClients.value.name }</option>
                            </c:forEach>
                        </select>
                    </div>
                    </c:if>
			</fieldset>
			<fieldset>
				<legend>Informations commande</legend>
				<label for="orderDate">Date : </label>
				<input type="text" id="orderDate" name="orderDate" value="<c:out value="${ order.date }"/>" />
				<p>${form.errors['orderDate']}</p>
			</fieldset>
			<p>${ form.result }</p>
			<input type="submit" value="Valider"/>
		</form>
	</div>
	
	<script src="<c:url value="/inc/jquery-3.3.1.min.js"/>"></script>
        
        <%-- jQuery : when the radio button is clicked, replace first part of the form by dropdown list --%>
        <script>
        	jQuery(document).ready(function(){
        		/* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire correspondant aux clients existants */
        		$("div#existingClient").hide();
        		/* 2 - Au clic sur un des deux boutons radio "choiceNewClient", on affiche le bloc d'éléments correspondant (nouveau ou ancien client) */
                jQuery('input[name=choiceNewClient]:radio').click(function(){
                	$("div#newClient").hide();
                	$("div#existingClient").hide();
                    var divId = jQuery(this).val();
                    $("div#"+divId).show();
                });
            });
        </script>
	
</body>
</html>