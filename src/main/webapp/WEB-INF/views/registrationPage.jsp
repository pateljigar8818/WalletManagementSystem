<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Registration</h3>

<form id="registration" action="${pageContext.request.contextPath}/registration" method="post" >
  <input type="text" name="email" placeholder="Email Address"/><br/>
  <input type="text" name="phoneNumber" placeholder="Phone Number"/><br/>
  <input type="text" name="address" placeholder="Address" /><br/>
  <input type="password" name="password" placeholder="Password" /><br/>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="submit" name="Submit"/><br/>
</form>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>