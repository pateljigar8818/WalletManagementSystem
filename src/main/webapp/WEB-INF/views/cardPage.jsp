<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>Add Card</h3>

<c:if test="${not empty message}">
	<h5>${message}</h5>
</c:if>

<form:form id="createWallet" action="" method="post" modelAttribute="card">
  <input type="text" name="cardNumber" placeholder="Card Number"/><form:errors path="cardNumber" cssClass="error" /><br/>
  <input type="text" name="expiryMonth" placeholder="Expiry Month"/><form:errors path="expiryMonth" cssClass="error" /><br/>
  <input type="text" name="expiryYear" placeholder="Expiry Year" /><form:errors path="expiryYear" cssClass="error" /><br/>
  <input type="text" name="nameOnCard" placeholder="Name On Card" /><form:errors path="nameOnCard" cssClass="error" /><br/>
  <select name="cardType">
  	<option value="0" selected>--Please select card--</option>
  	<option value="1" >Debit Card</option>
  	<option value="2" >Credit Card</option>
  	<option value="3" >PrePaid Card</option>
  </select><form:errors path="cardType" cssClass="error" /><br/>
  <input type="password" name="cvv" placeholder="CVV" /><form:errors path="cvv" cssClass="error" /><br/>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="submit" name="Submit"/><br/>
</form:form>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>