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
                <li><a href="<c:url value='/team?type=owner'/>">Teams</a></li>
            </ul>
        </div>
        <div class="nav-right">
            <ul class="nav-links">
                <c:choose>
                    <c:when test="${isAuthorized}">
                        <li><a href="<c:url value='/profile'/>" class="nav-link">
                            <c:choose>
                                <c:when test="${empty user.photoUrl}">
                                    <img src="<c:url value='/img/tomato_account.png'/>" alt="profile" />
                                </c:when>
                                <c:otherwise>
                                    <img src="<c:url value="${user.photoUrl}"/>" alt="${user.name}'s photo" style="height: 60px; width: 60px; border-radius: 50px">
                                </c:otherwise>
                            </c:choose>
                            <div id="account-div">Account</div>
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
