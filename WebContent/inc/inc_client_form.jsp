<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="clientName">Nom : </label>
<input type="text" id="clientName" name="clientName" value="<c:out value="${ client.name }"/>"/>
<p>${form.errors['clientName']}</p>