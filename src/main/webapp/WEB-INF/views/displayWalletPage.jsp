<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
<h3>Display Wallet Page</h3>

<c:if test="${not empty message}">
	<h5>${message}</h5>
</c:if>

<c:if test="${not empty cards}">
<table>
<thead>
    <tr>      
        <td>Name On Card</td>
        <td>Card Number</td>
        <td>Expiry Month/Year</td>
        <td>CVV</td>
        <td>CardType</td>
    </tr>
</thead>
<tbody>
<c:forEach items="${cards}" var="card">
    <tr>      
        <td>${card.nameOnCard}</td>
        <td>${card.cardNumber}</td>
        <td>${card.expiryMonth}/${card.expiryYear}</td>
        <td>${card.cvv}</td>
        <td>${card.cardType.cardTypeDesc}</td>
    </tr>
</c:forEach>
</tbody>
</table>
<a href="${pageContext.request.contextPath}/user/landingPage">Home</a> <br/>
</c:if>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>