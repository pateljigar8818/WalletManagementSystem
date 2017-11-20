<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>User Page</h3>

<c:if test="${not empty message}">
	<h5>${message}</h5>
</c:if>

<a href="${pageContext.request.contextPath}/user/displayWallet">Display Wallet</a> <br/>

<a href="${pageContext.request.contextPath}/user/addCard">Add card</a>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>