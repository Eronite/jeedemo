<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des commandes existantes</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
    </head>
    <body>
        <c:import url="/inc/menu.jsp" />
        <div>
        <c:choose>
            <%-- if there is no order in session (orders map empty), display message --%>
            <c:when test="${ empty sessionScope.orders }">
                <p class="error">Aucune commande enregistrée.</p>
            </c:when>
            <%-- else display table --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Client</th>
                    <th>Date</th>
                    <th class="action"></th>                    
                </tr>
                <%-- iteration over orders map (in session), and using varStatus object for counting loops --%>
                <c:forEach items="${ sessionScope.orders }" var="mapOrders" varStatus="loop">
                <%-- counting loops for css colors (loop.index also works) --%>
                <tr class="${loop.count % 2 == 0 ? 'even' : 'odd'}">
                    <%-- display Order bean property, stored as current value in mapOrders --%>
                    <td><c:out value="${ mapOrders.value.client.firstName } ${ mapOrders.value.client.name }"/></td>
                    <td><c:out value="${ mapOrders.value.date }"/></td>
                    <%-- link to removing servlet, with orders's date (key of the map) as parameter (<c:param/> tag) --%>
                    <td class="action">
                        <a href="<c:url value="/RemoveOrder"><c:param name="orderDate" value="${ mapOrders.key }" /></c:url>">
                            <img src="<c:url value="/inc/remove.png"/>" alt="Remove" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>