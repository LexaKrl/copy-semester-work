<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/profile/profile.css'/>"/>
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<body>
<%@ include file="/template/base/navbar.jsp" %>

<div id="main-container">
    <div id="photo-name-box" class="box">
        <div id="photo-container">

            <c:choose>
                <c:when test="${empty user.photoUrl}">
                    <img src="<c:url value='/img/default_tomato_profile_photo.jpg'/>" alt="${user.name}'s photo">
                </c:when>
                <c:otherwise>
                    <img src="<c:url value="${user.photoUrl}"/>" alt="${user.name}'s photo">
                </c:otherwise>
            </c:choose>

        </div>
        <div id="name-container">
            <h2>${user.name} ${user.lastname}</h2>
        </div>
    </div>
    <div id="secondary-info-box" class="box">

        <h2>Login:</h2>
        <div class="field">
            <a>${user.login}</a>
        </div>

        <h2>Password:</h2>
        <div class="field">
            <a>********************</a>
        </div>

        <h3>Description</h3>
        <div class="description-box">
            <a>${user.description}</a>
        </div>
    </div>
    <div id="profile-link-box">
        <a href="<c:url value="/profile/edit"/>" class="button">Edit profile</a>
        <form action="/logout" method="post">
            <input type="submit" class="button" value="Logout">
        </form>
    </div>
</div>

</body>
<%@include file="/template/base/footer.jsp"%>
</html>