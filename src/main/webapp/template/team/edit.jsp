<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/team/create-join-team.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
    <title>Edit your team</title>
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="main-box" class="box">
        <h1>Edit your team</h1>
        <form method="post" action="/edit-team">
            <input id="team-id" type="hidden" value="${team.id}">
            <label for="name">
                Change your team name:
                <input id="name" name="name" type="text" value="${team.name}" required>
            </label>
            <label for="new-password">
                (Optional) Enter password for your team (If you want to change it):
                <input id="new-password" name="new-password" type="password">
            </label>
            <label for="password">
                (Required, to prove your changes) Enter password for your team:
                <input id="password" name="password" type="password" required>
            </label>
            <input type="submit" value="Edit team">
        </form>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
