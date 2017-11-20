<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Add Card</h3>

<c:if test="${not empty message}">
	<h5>${message}</h5>
</c:if>

<form id="createWallet" action="" method="post" >
  <input type="text" name="cardNumber" placeholder="Card Number"/><br/>
  <input type="text" name="expiryMonth" placeholder="Expiry Month"/>
  <input type="text" name="expiryYear" placeholder="Expiry Year" /><br/>
  <input type="text" name="nameOnCard" placeholder="Name On Card" /><br/>
  <select name="cardType">
  	<option value="0" selected>--Please select card--</option>
  	<option value="1" >Debit Card</option>
  	<option value="2" >Credit Card</option>
  	<option value="3" >PrePaid Card</option>
  </select><br/>
  <input type="password" name="cvv" placeholder="CVV" /><br/>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="submit" name="Submit"/><br/>
</form>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>