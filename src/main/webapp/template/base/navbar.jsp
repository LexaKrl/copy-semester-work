<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="navbar">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/base/navbar.css"/>">
    <div class="nav-container">
        <a href="<c:url value="/"/>" class="logo">
            <img src="<c:url value='/img/tomatoject_logo.png'/>" alt="Logo"/>
        </a>
        <div class="nav-left">
            <ul class="nav-links">
                <li><a href="<c:url value='/about'/>">About</a></li>
                <li><a href="<c:url value='/features'/>">Features</a></li>
                <li><a href="<c:url value='/projects'/>">Projects</a></li>
            </ul>
        </div>
        <div class="nav-right">
            <ul class="nav-links">
                <c:choose>
                    <c:when test="${isAuthorized}">
                        <li><a href="<c:url value='/profile'/>" class="nav-link">
                            <img src="<c:url value='/img/tomato_account.png'/>" alt="profile" />
                        </a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value='/register'/>">Sign Up</a></li>
                        <li><a href="<c:url value='/login'/>">Log In</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
