<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Admin Page</h3>

<a href="${pageContext.request.contextPath}/admin/getAllUsers">Users</a>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>