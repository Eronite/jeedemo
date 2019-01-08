<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Client</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<div>
		<form method="post" action="<c:url value="/ClientCreation"/>">
			<fieldset>
				<legend>Informations client</legend>
				<c:import url="/inc/inc_client_form.jsp" />
			</fieldset>
			<p>${ form.result }</p>
			<input type="submit" value="Valider"/>
		</form>
	</div>
</body>
</html>