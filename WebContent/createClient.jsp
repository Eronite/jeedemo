<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Client</title>
</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<div>
		<form method="get" action="<c:url value="/ClientCreation"/>">
			<fieldset>
				<legend>Informations client</legend>
				<c:import url="/inc/inc_client_form.jsp" />
			</fieldset>
			<input type="submit" value="Valider"/>
		</form>
	</div>
</body>
</html>