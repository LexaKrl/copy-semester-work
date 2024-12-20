<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage data</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/project/manage.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="change-data-box" class="box">
        <h1>Edit your project</h1>
        <div id="form-container">
            <form method="post" action="/edit-project">
                <input id="team-id" name="team-id" type="hidden" value="${teamId}">
                <input id="projectId" name="projectId" type="hidden" value="${project.id}">
                <input id="owner-id" name="owner-id" type="hidden" value="${project.projectOwnerId}">
                <label for="name"><h2>Enter name for your project:</h2></label><input id="name" name="name"
                                                                            value="${project.name}" type="text">
                <br>
                <label for="description"><h2>Enter description:</h2></label><input id="description" name="description"
                                                                         value="${project.description}" type="text">
                <input type="submit" value="edit">
            </form>
        </div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
