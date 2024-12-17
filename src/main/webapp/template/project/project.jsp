<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Project</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/project/project.css'/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>
<%@ include file="/template/base/navbar.jsp" %>
<body>
<div id="main-container">
    <div id="project-overview" class="box">
        <div class="container">
            <h1>Project Overview</h1>
            <p>Welcome to the project page! Here you can find details about your project and its objectives.</p>
        </div>
    </div>

    <div id="project-details" class="box">
        <div class="container">
            <h2>Project Details</h2>
            <p><strong>Project Name:</strong> Awesome Project</p>
            <p><strong>Description:</strong> This project aims to revolutionize the way we manage tasks.</p>
            <p><strong>Deadline:</strong> December 31, 2023</p>
        </div>
    </div>

    <div id="project-members" class="box">
        <div class="container">
            <h2>Project Members</h2>
            <ul>
                <li>
                    <h3>John Doe</h3>
                    <p>Role: Project Manager</p>
                    <img src="<c:url value='/img/john_doe.png'/>" alt="John Doe" />
                </li>
                <li>
                    <h3>Jane Smith</h3>
                    <p>Role: Developer</p>
                    <img src="<c:url value='/img/jane_smith.png'/>" alt="Jane Smith" />
                </li>
                <li>
                    <h3>Emily Johnson</h3>
                    <p>Role: Designer</p>
                    <img src="<c:url value='/img/emily_johnson.png'/>" alt="Emily Johnson" />
                </li>
                <!-- Add more project members as needed -->
            </ul>
        </div>
    </div>

    <div id="project-actions" class="box">
        <div class="container">
            <h2>Project Actions</h2>
            <p>What would you like to do?</p>
            <ul>
                <li><a class="page-link" href="<c:url value='/add-task'/>">Add a New Task</a></li>
                <li><a class="page-link" href="<c:url value='/view-tasks'/>">View All Tasks</a></li>
                <li><a class="page-link" href="<c:url value='/manage-project'/>">Manage Project</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
<%@ include file="/template/base/footer.jsp" %>
</html>