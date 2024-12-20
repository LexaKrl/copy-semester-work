<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/team/manage.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
    <title>Manage your team</title>
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="main-delete-create-container">
        <div id="main-box" class="box">
            <h1>Edit your team</h1>
            <form method="post" action="/manage-team">
                <input id="team-id" name="team-id" type="hidden" value="${team.id}">
                <label for="name">
                    Change your team name:
                    <input id="name" name="name" type="text" value="${team.name}" required>
                </label>
                <label for="password">
                    (Required, to prove your changes) Enter password for your team:
                    <input id="password" name="password" type="password" required>
                </label>
                <input type="submit" value="Edit team">
            </form>
            <form method="post" action="/delete-team">
                <input id="delete-team-id" name="delete-team-id" type="hidden" value="${team.id}">
                <input type="submit" value="Delete team">
            </form>
        </div>
        <div id="delete-create-project-box" class="box">
            <h1>Create Project</h1>
            <div id="create-project-box" class="box">
                <h2>Create new project</h2>
                <form method="post" action="/create-project">
                    <label for="project-name">Enter your project name:</label>
                    <input id="project-name" name="name" type="text" required>
                    <input id="project-owner-id" name="project-owner-id" type="hidden" value="${userId}" required>
                    <input id="project-team-id" name="project-team-id" type="hidden" value="${team.id}" required>

                    <input type="submit" value="Create project">
                </form>
            </div>
            <div id="delete-project-box" class="box">
                <form method="post" action="/delete-project">
                    <input name="project-team-id" type="hidden" value="${team.id}" required>
                    <label for="project-id">Enter id for the deletion:</label>
                    <input id="project-id" name="project-id" type="text" placeholder="Enter project id to delete">
                    <input type="submit" value="Delete project">
                </form>
            </div>
            <div id="message-block">
                <c:if test="${not empty message}">
                    <div id="message-box" class="box">
                        <a style="color: red;">${message}</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div id="wrapper-projects">
        <div id="manage-projects" class="box">
            <c:choose>
                <c:when test="${not empty projects}">
                    <h1>Your projects to manage</h1>
                    <h2>Project name | Project id | Owner id</h2>
                    <c:forEach var="project" items="${projects}">
                        <div id="project-view" class="box">
                            <a href="<c:url value='/team/${team.id}/project/${project.id}'/>">
                                <span>${project.name} |</span>
                                <span>${project.id} |</span>
                                <span>${project.projectOwnerId}</span>
                            </a>
                            <a href="<c:url value="/team/${team.id}/project/${project.id}/manage-data"/>" class="button">Manage data</a>
                            <a href="<c:url value="/team/${team.id}/project/${project.id}/manage-members"/>" class="button">Manage members</a>
                            <a href="<c:url value="/team/${team.id}/project/${project.id}/manage-posts"/>" class="button">Manage posts</a>
                        </div>
                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <h2>Your team have no projects</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
