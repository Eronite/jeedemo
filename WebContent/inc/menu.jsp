<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="menu">
    <p><a href="<c:url value="/ClientCreation"/>">Créer un nouveau client</a></p>
    <p><a href="<c:url value="/OrderCreation"/>">Créer une nouvelle commande</a></p>
    <p><a href="<c:url value="/ListClients"/>">Voir clients existants</a></p>
    <p><a href="<c:url value="/ListOrders"/>">Voir commandes existantes</a></p>
</div>