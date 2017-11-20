<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>Registration</h3>

<form:form id="registration" action="${pageContext.request.contextPath}/registration" method="post"  modelAttribute="registration">
  <input type="text" name="email" placeholder="Email Address"/><form:errors path="email" cssClass="error" /><br/>
  
  <input type="text" name="firstName" placeholder="First Name"/><form:errors path="firstName" cssClass="error" /><br/>
  <input type="text" name="lastName" placeholder="Last Name"/><form:errors path="lastName" cssClass="error" /><br/>
  <input type="text" name="phoneNumber" placeholder="Phone Number"/><form:errors path="phoneNumber" cssClass="error" /><br/>
  <input type="text" name="address" placeholder="Address" /><form:errors path="address" cssClass="error" /><br/>
  <input type="password" name="password" placeholder="Password" /><form:errors path="password" cssClass="error" /><br/>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="submit" name="Submit"/><br/>
</form:form>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>