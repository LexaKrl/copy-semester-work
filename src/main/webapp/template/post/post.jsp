<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${Post.name}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/post/post.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="post-name-description-box" class="box">
        <h1>${post.name}</h1>
        <div id="description-box" class="box">
            <h1>Description</h1>
            <br>
            <h2>${post.description}</h2>
            <br>
            <br>
            <c:choose>
                <c:when test="${not empty post.photoUrl}">
                    <img src="${post.photoUrl}" alt="${post.name}'s photo">
                </c:when>
                <c:otherwise>
                    Its better to download photo to the post!
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div id="post-data">
        <h2>Project ID: ${post.projectId}</h2>
        <h2>Team ID: ${teamId}</h2>
        <h2>Assignee ID: ${post.assigneeId}</h2>
        <p>Status: <span class="status">${post.completed}</span></p>
    </div>

    <div id="link-box" class="box">
        <a href="<c:url value="/team/${teamId}/project/${post.projectId}/post/${post.id}/edit"/>" class="button">Edit</a>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
