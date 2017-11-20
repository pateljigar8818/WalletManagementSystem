<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Login</h3>

<c:if test="${not empty message}">
	<h5>${message}</h5>
</c:if>

<form id="login" action="${pageContext.request.contextPath}/login" method="post" >
  <input type="text" name="username" placeholder="UserName"/><br/>
  <input type="password" name="password" placeholder="Password"/><br/>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="submit" name="Submit"/><br/>
</form>

