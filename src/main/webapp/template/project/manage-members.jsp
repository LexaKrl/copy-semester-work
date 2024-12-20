<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage members</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/project/manage.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
    <script rel="stylesheet" src="/js/project/manage-members.js"></script>
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="project-members-box" class="box">
        <div id="project-table">
            <h1>Your project members</h1>
            <c:choose>
                <c:when test="${not empty projectMembers}">
                    <div id="project-table-box" class="box">
                        <h2>Member name  |  Member lastname  |  Member id</h2>
                        <c:forEach items="${projectMembers}" var="member">
                            <div id="project-member-view" class="box">
                                <span>${member.name}</span>
                                <span>${member.lastname}</span>
                                <span>${member.id}</span>
                            </div>
                            <form action="/leave-project/${member.id}" method="post">
                                <input type="hidden" name="teamId" value="${teamId}">
                                <input type="hidden" name="projectId" value="${projectId}">
                                <input type="hidden" name="isOwner" value="${isOwner}">
                                <input type="submit" class="button" value="kick">
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
    <div id="team-members-box" class="box">
        <div id="team-table">
            <h1>Your team members</h1>
            <c:choose>
                <c:when test="${not empty teamMembers}">
                    <div id="team-table-box" class="box">
                        <h2>Member name  |  Member lastname  |  Member id</h2>
                        <c:forEach items="${teamMembers}" var="member">
                            <c:set var="isInProject" value="false" />
                            <c:forEach items="${projectMembers}" var="projectMember">
                                <c:if test="${member.id == projectMember.id}">
                                    <c:set var="isInProject" value="true" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${isInProject == false}">
                                <div id="team-member-view" class="box">
                                    <span>${member.name}</span>
                                    <span>${member.lastname}</span>
                                    <span>${member.id}</span>
                                    <form action="/join-project/${member.id}" method="post">
                                        <input type="hidden" name="teamId" value="${teamId}">
                                        <input type="hidden" name="projectId" value="${projectId}">
                                        <input type="hidden" name="isOwner" value="${isOwner}">
                                        <input type="submit" class="button" value="add">
                                    </form>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <h2>Wow! All the team members are work in your project!!</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>
