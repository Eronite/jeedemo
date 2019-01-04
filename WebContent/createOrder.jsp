<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Order</title>
<%-- <link type="text/css" rel="stylesheet" href="inc/style.css"/> --%>
</head>
<body>
		<div>
		<form method="get" action="OrderCreation">
			<fieldset>
				<legend>Informations client</legend>
				<label for="clientName">Nom : </label>
				<input type="text" id="clientName" name="clientName"/>
			</fieldset>
			<fieldset>
				<legend>Informations commande</legend>
				<label for="orderDate">Date : </label>
				<input type="text" id="orderDate" name="orderDate"/>
			</fieldset>
			<input type="submit" value="Valider"/>
		</form>
	</div>
</body>
</html>