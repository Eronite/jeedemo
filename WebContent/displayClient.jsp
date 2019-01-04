<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Affichage client</title>
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<p>Nom : <c:out value="${ client.name }"/></p>
</body>
</html>