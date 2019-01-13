<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="clientName">Nom : </label>
<input type="text" id="clientName" name="clientName" value="<c:out value="${ client.name }"/>"/>
<p>${form.errors['clientName']}</p>

<label for="clientFirstName">Prénom : </label>
<input type="text" id="clientFirstName" name="clientFirstName" value="<c:out value="${ client.firstName }"/>"/>
<p>${form.errors['clientFirstName']}</p>