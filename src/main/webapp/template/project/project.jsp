<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Project</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/project/project.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
    <script rel="stylesheet" src="<c:url value="/js/project/project.js"/>"></script>
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="project-overview" class="box">
        <div class="container">
            <h1>${project.name}</h1>
            <c:if test="${isOwner}">
                <div id="manage-link">
                    <a href="<c:url value="/team/${teamId}/project/${project.id}/manage-data"/>" id="member-data" class="button">Manage data</a>
                    <a href="<c:url value="/team/${teamId}/project/${project.id}/manage-members"/>" id="manage-member" class="button">Manage member</a>
                    <a href="<c:url value="/team/${teamId}/project/${project.id}/manage-posts"/>" id="manage-post" class="button">Manage post</a>
                </div>
            </c:if>
        </div>
        <div id="description-box" class="box">
            <h2>Description</h2>
            <h3>${project.description}</h3>
        </div>
    </div>

    <div id="projects-button">
        <%-- for down link href="<c:url value="/team/${team.id}/project/${project.id}/post/${post.id}"/>" --%>
        <a href="<c:url value="/team/${teamId}/project/${project.id}?type=get-all-posts#post-container"/>" id="get-all-posts" class="button">All</a>
        <a href="<c:url value="/team/${teamId}/project/${project.id}?type=get-assignee-to-me#post-container"/>" id="get-assignee-to-me" class="button">ToMe</a>
        <a href="<c:url value="/team/${teamId}/project/${project.id}?type=get-all-completed#post-container"/>" id="get-all-completed" class="button">Completed</a>
    </div>

    <div id="post-wrapper">
        <h1>Posts</h1>
        <c:choose>
            <c:when test="${not empty posts}">
                <div id="post-container">
                    <c:forEach items="${posts}" var="post">
                        <div class="post-box">
                            <a href="<c:url value='/team/${teamId}/project/${project.id}/post/${post.id}'/>" class="post-name">
                                <h2>${post.name}</h2>
                            </a>
                            <p><strong>Assignee to:</strong> ${post.assigneeId}</p>
                            <c:if test="${post.completed}">
                                <p class="completed-status">Completed!!</p>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div id="post-container">
                    <h2>There are no posts here...</h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>