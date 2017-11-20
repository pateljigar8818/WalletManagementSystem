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
<h3>List Users Page</h3>

<c:if test="${not empty message}">
	<h5>${message}</h5>
</c:if>

<c:if test="${not empty users}">
<table>
<thead>
    <tr>      
        <td>User Name</td>
        <td>Email</td>
        <td>Wallet Active</td>
        <td>Web UI Active</td>
        <td>Delete Wallet</td>
    </tr>
</thead>
<tbody>
<c:forEach items="${users}" var="user">
    <tr>      
        <td>${user.firstName} ${user.lastName} </td>
        <td>${user.email}</td>
        <c:choose>
        	<c:when test="${not empty user.wallet}">
		        <td>${user.wallet.active} </td>
		        <td>${user.wallet.webInterfaceActive}</td>
		        <td>${user.wallet.deleted}</td>
        	</c:when>
        	<c:otherwise>
		        <td>-</td>
		        <td>-</td>
		        <td>-</td>
        	</c:otherwise>
        </c:choose>
    </tr>
</c:forEach>
</tbody>
</table>
</c:if>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>