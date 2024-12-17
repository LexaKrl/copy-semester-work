<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Join team</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/team/create-join-team.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="join-team-box" class="box">
        <h1>Join team!</h1>
        <br>
        <form method="post" action="/join-team">
            <label for="team-id">
                Enter the ID of the team you want to join:
                <input id="team-id" name="team-id" type="text" required>
            </label>
            <label for="name">
                Enter name of the team you want to join:
                <input id="name" name="name" type="text" required>
            </label>
            <label for="password">
                Enter password for your team:
                <input id="password" name="password" type="password" required>
            </label>
            <input type="submit" value="Join team">
        </form>
        <div id="message">
            <c:if test="${not empty message}">
                <p>${message}</p>
            </c:if>
        </div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
