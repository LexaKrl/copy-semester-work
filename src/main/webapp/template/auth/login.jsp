<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel='stylesheet' type='text/css' href="<c:url value='/css/login-register.css'/>"/>
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<body>
<div id="main-container">
    <div id="form-container">
        <div id="message">
            <h3>Welcome back!</h3>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/login">

            <label>
                Login:
                <input name="login" type="text" placeholder="Enter your login">
            </label>
            <br>
            <label>
                Password:
                <input name="password" type="password" placeholder="Enter your password">
            </label>
            <br>
            <input type="submit" value="Login">
            <br>
        </form>
    </div>
    <div id="link-box">
        <a href="<c:url value="/register"/>">Are you new there?</a>
    </div>
    <div id="errorMessageDiv">
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p>
        </c:if>
    </div>
</div>
</body>
</html>
