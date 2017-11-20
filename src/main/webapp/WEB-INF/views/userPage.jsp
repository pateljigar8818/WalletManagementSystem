<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>User Page</h3>

<c:choose>
    <c:when test="${emptyWallet}">
		<a href="${pageContext.request.contextPath}/wallet/createWallet">Create Wallet</a>
    </c:when>    
    <c:otherwise>
    	<h5>Your wallet may be inactive or not authorize to view or deleted. Please contact administrator</h5>
    </c:otherwise>
</c:choose>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>