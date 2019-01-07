<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Order</title>
<%-- <link type="text/css" rel="stylesheet" href="inc/style.css"/> --%>
</head>
<body>
	<div>
		<form method="post" action="<c:url value="/OrderCreation"/>">
			<%-- placer le client accessible via l'EL ${ commande.client }
			dans une variable "client" de portée request, pour que le code du fragment fonctionne à la fois
			depuis le formulaire de création d'un client et depuis celui de création d'une commande. --%>
            <c:set var="client" value="${ order.client }" scope="request" />
			<fieldset>
				<legend>Informations client</legend>
				<c:import url="/inc/inc_client_form.jsp" />
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
</body>
</html>