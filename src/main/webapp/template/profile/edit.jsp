<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/profile/edit.css'/>"/>
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
                    <img src="${user.photoUrl}" alt="${user.name}'s photo">
                </c:otherwise>
            </c:choose>

        </div>
        <form method="post" action="/upload" enctype="multipart/form-data">
            <input type="file" name="file" id="photo" accept="image/*" required>
            <br>
            <button type="submit">Upload</button>
        </form>

        <form method="post" action="/profile/edit">
            <div id="name-container">
                <label for="name">Enter your name:</label>
                <input id="name" name="name" type="text" value="${user.name}" required>
                <br>
                <label for="lastname">Enter your lastname:</label>
                <input id="lastname" name="lastname" type="text" value="${user.lastname}" required>
            </div>
            <label for="login">Enter your login:</label>
            <input id="login" type="text" name="login" value="${user.login}" required>
            <c:if test="${not empty message}">
                <div id="message">
                        ${message}
                </div>
            </c:if>
            <br>
            <div class="description-box">
                <label for="description">Description</label>
                <input id="description" type="text" name="description" value="${user.description}">
            </div>
            <input type="submit" value="Submit data">
        </form>
        <form method="post" action="/profile/edit/password">
            <h2>Change password</h2>

            <label for="old-password">Enter your old password:</label>
            <input id="old-password" type="password" name="old-password" required>

            <label for="new-password">Enter your new password:</label>
            <input id="new-password" type="password" name="new-password" required>

            <label for="new-password-approve">Enter your new password:</label>
            <input id="new-password-approve" type="password" name="new-password-approve" required>
            <br>
            <c:if test="${not empty passwordMessage}">
                <div id="message">
                        ${passwordMessage}
                </div>
            </c:if>
            <br>
            <input type="submit" value="Submit password">
        </form>
        <form action="/profile/edit/delete" method="post">
            <input type="hidden" name="_method" value="DELETE">
            <input type="submit" value="Delete account">
        </form>
    </div>
</div>
</body>
<%@include file="/template/base/footer.jsp"%>
</html>
