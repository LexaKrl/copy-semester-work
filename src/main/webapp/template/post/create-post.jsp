<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Post</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/post/create-post.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>

<div id="main-container">
    <div id="form-box" class="box">
        <h1>Create post</h1>
        <form action="/assign-post/${assigneeId}" method="post">
            <label for="name">Name:</label><input id="name" name="name" type="text" placeholder="Enter post name">
            <label for="description">Description:</label><textarea id="description" name="description" placeholder="Enter description here"></textarea>
            <label for="projectId">Project ID:</label><input type="text" id="projectId" name="projectId" value="${projectId}">
            <label for="teamId">Team ID:</label><input type="text" id="teamId" name="teamId" value="${teamId}">
            <label for="assigneeId">Assignee ID:</label><input type="text" id="assigneeId" name="assigneeId" value="${assigneeId}">
            <input type="submit" value="create post">
        </form>
    </div>
</div>

</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
