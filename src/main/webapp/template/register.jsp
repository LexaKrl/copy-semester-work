<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login-register.css"/>" />
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<body>
<div id="main-container">
    <div id="form-container">
        <div id="message">
            <h3>Welcome! We are waiting for you!</h3>
        </div>
        <form method="post" action="/register">

            <div id="name-lastname-line">
                <label>
                    Name:
                    <input name="name" type="text" placeholder="Enter your name" required>
                </label>

                <label>
                    Lastname:
                    <input name="lastname" type="text" placeholder="Enter your lastname" required>
                </label>
            </div>
            <br>
            <div id="login-password-line">
                <label>
                    Login:
                    <input name="login" type="text" placeholder="Enter your login" required>
                </label>

                <label>
                    Password:
                    <input name="password" type="password" placeholder="Enter your name" required>
                </label>
            </div>
            <br>
            <input type="submit" value="Register">
            <br>
        </form>
    </div>
    <div id="link-box">
        <a href="<c:url value="/login"/>">Already have an account?</a>
    </div>
    <div id="errorMessageDiv">
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p>
        </c:if>
    </div>
</div>
</body>
</html>
