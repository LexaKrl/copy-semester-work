<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Team ${team.name}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/team/team.css"/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="top-box" class="box"> <h1>${team.name}</h1> </div>
    <div id="link-box" class="box">
        <a href="#member-pointer" class="button">
            Members
        </a>
        <a href="#project-pointer" class="button">
            Projects
        </a>
        <c:if test="${isOwner}">
            <a href="/team/${team.id}/manage">
                Manage team
            </a>
        </c:if>
    </div>
    <div id="project-box" class="box">
        <c:choose>
            <c:when test="${not empty projects}">
                <div id="project-table" class="box">
                    <h2>Project name  |  Project id  |  Owner id</h2>
                    <c:forEach items="${projects}" var="project">
                        <div id="project-view" class="box">
                            <a href="<c:url value='/team/${team.id}/project/${project.id}'/>">
                                <span>${project.name}  |</span>
                                <span>${project.id}  |</span>
                                <span>${project.projectOwnerId}</span>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <h2>Your team have no projects</h2>
            </c:otherwise>
        </c:choose>
        <div id="project-pointer"></div>
    </div>
    <div id="member-box" class="box">
        <div id="member-content" class="box">
            <h2>Member name | Member lastname | Member id</h2>
            <c:forEach items="${members}" var="member">
                <div id="member-view" class="box">
                    <span>${member.name} </span>
                    <span>${member.lastname} </span>
                    <span>${member.id}</span>
                    <c:if test="${isOwner && member.id != team.ownerId}">
                        <form action="/team-delete-user/${member.id}" method="post">
                            <input id="delete-team-id" name="delete-team-id" type="hidden" value="${team.id}">
                            <input type="submit" value="kick" class="button">
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div id="member-pointer"></div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
