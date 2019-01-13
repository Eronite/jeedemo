<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Affichage commande</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<p>Nom : <c:out value="${ order.client.name }"/></p>
	<p>Prénom : <c:out value="${ order.client.firstName }"/></p>
	<p>Date : <c:out value="${ order.date }"/></p>
</body>
</html>