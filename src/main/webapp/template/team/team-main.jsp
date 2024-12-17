<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Team Main Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/team/team-main.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="<c:url value="/js/team/team-main.js"/>"></script>
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="team-box" class="box">
        <div class="container">
            <h1>Your teams</h1>
            <br>
            <div id="projects-button">
                <a id="owner-link" href="<c:url value="/team?type=owner"/>" class="button">You owner</a>
                <a id="member-link" href="<c:url value="/team?type=member"/>" class="button">You member</a>
            </div>
            <c:choose>
                <c:when test="${not empty teams}">
                    <div id="table-box" class="box">
                            <h2>Team name  |  Team id  |  Owner id</h2>
                            <c:forEach items="${teams}" var="team">
                                <div id="team-view" class="box">
                                    <a href="<c:url value='/team/${team.id}'/>">
                                        <span>${team.name}</span>
                                        <span>${team.id}</span>
                                        <span>${team.ownerId}</span>
                                    </a>
                                </div>
                            </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <h2>You have no teams. Create your own or join to existing!</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div id="team-actions" class="box">
        <div class="container">
            <h1>Team Actions</h1>
            <h2>What would you like to do?</h2>
            <ul>
                <li><a class="page-link" href="<c:url value='/create-team'/>">Create a New Team</a></li>
                <li><a class="page-link" href="<c:url value='/join-team'/>">Join an Existing Team</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>