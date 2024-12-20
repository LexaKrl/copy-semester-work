<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Post</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/post/edit-post.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <h1>Edit Post</h1>
    <form action="/edit-post/${post.id}" method="post">
        <div class="form-group">
            <label for="name">Post Name:</label>
            <input type="text" id="name" name="name" value="${post.name}" required>
        </div>

        <div class="form-group">
            <label for="description">Post Description:</label>
            <textarea id="description" name="description" rows="5" required>${post.description}</textarea>
        </div>

        <div class="form-group">
            <label for="completed">Status:</label>
            <select id="completed" name="completed">
                <option value="true" ${post.completed ? "selected" : ""}>Completed</option>
                <option value="false" ${!post.completed ? "selected" : ""}>In Progress</option>
            </select>
        </div>

        <div class="form-buttons">
            <input type="hidden" name="teamId" value="${teamId}">
            <input type="hidden" name="projectId" value="${post.projectId}">
            <input type="hidden" name="postId" value="${post.id}">
            <input type="submit" value="Save Changes" class="button">
            <a href="<c:url value='/team/${teamId}/project/${post.projectId}/post/${post.id}'/>" id="cancel-button">Cancel</a>
        </div>
    </form>
    <br>
    <form method="post" action="/delete-post/${post.id}" class="delete-form">
        <input type="hidden" name="teamId" value="${teamId}">
        <input type="hidden" name="projectId" value="${post.projectId}">
        <input type="submit" class="button delete" value="Delete Post">
    </form>
    <br>
    <form method="post" action="/upload-post-photo" enctype="multipart/form-data">
        <div class="form-group">
            <input type="hidden" name="teamId" value="${teamId}">
            <input type="hidden" name="projectId" value="${post.projectId}">
            <input type="hidden" name="postId" value="${post.id}">
            <input type="file" name="file" id="photo" accept="image/*">
            <br>
            <input type="submit" class="button" value="Upload">
        </div>
    </form>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>