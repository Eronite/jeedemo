<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Client</title>
</head>
<body>
	<div>
		<form method="get" action="ClientCreation">
			<fieldset>
				<legend>Informations client</legend>
				<label for="clientName">Nom : </label>
				<input type="text" id="clientName" name="clientName"/>
			</fieldset>
			<input type="submit" value="Valider"/>
		</form>
	</div>
</body>
</html>