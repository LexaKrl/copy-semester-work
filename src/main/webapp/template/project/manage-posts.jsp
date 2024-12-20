<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assign Posts</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/project/manage.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
    <script rel="stylesheet" src="<c:url value="/js/project/manage-posts.js"/>"></script>
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="post-list-box" class="box">
        <h1>Assign post to</h1>
        <div id="member-table">
            <c:choose>
                <c:when test="${not empty members}">
                    <div id="project-table-box" class="box">
                        <h2>Member name  |  Member lastname  |  Member id  |  Description</h2>
                        <c:forEach items="${members}" var="member">
                            <div id="project-member-view" class="box">
                                <span>${member.name}</span>
                                <span>${member.lastname}</span>
                                <span>${member.id}</span>
                                <span>${member.description}</span>
                                <button class="toggle-form" data-member-id="${member.id}">Assign</button>
                            </div>
                            <form id="assign-form-${member.id}" action="/assign-post/${member.id}" method="post" style="display: none;">
                                <label for="name-${member.id}">Name:</label>
                                <input type="text" id="name-${member.id}" name="name">
                                <label for="description-${member.id}">Description:</label>
                                <input type="text" id="description-${member.id}" name="description">
                                <input type="hidden" id="teamId-${member.id}" name="teamId" value="${teamId}">
                                <input type="hidden" id="projectId-${member.id}" name="projectId" value="${projectId}">
                                <input type="hidden" id="isOwner-${member.id}" name="isOwner" value="${isOwner}">
                                <input type="submit" class="button" value="Assign">
                            </form>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <h2>Seems like there is no members on your project. Add the new one!!</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
