<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/welcome.css"/>">
    <link rel="icon" href="<c:url value='/img/tomatoject_favicon_no_background.png'/>" type="image/png">
</head>

<%@include file="/template/base/navbar.jsp" %>

<body>
<div id="wrapper">
  <div id="greeting-box" class="box">
      <div class="container">
          <h1>Welcome to Tomatoject!</h1>
          <br>
          <h2><a class="page-link" href="<c:url value="/register"/>">Sign in</a> if you are new on our site</h2>
          <br>
          <h2>Or <a href="<c:url value="/login"/>">Log in</a> if you are already have account</h2>
      </div>
  </div>
<div id="feature-box" class="box">
      <div class="container">
          <h1>Figure out what you can do on our site</h1>
          <br>
          <h2>Create your team or join in your friend's one</h2>
          <br>
          <h2>Create a project to start your team work</h2>
      </div>
  </div>
  <div id="project-box" class="box">
      <div class="container">
          <h1>Work in your project</h1>
          <br>
          <h2>Do post to define work process</h2>
          <br>
          <h2>Response on posts with work reports and images</h2>
      </div>
  </div>
</div>
</body>
</html>
