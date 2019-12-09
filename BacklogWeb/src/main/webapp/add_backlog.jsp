<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SEBIRE
  Date: 23/11/2018
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter au backlog</title>
    <link rel="stylesheet" type="text/css" href="asset/css/base.css" >
    <link rel="stylesheet" type="text/css" href="asset/css/spectre.min.css">
    <link rel="stylesheet" type="text/css" href="asset/css/spectre-exp.min.css">
    <link rel="stylesheet" type="text/css" href="asset/css/spectre-icons.min.css">
    <link rel="icon" href="favicon.ico" />
</head>
<body>
<header class="navbar" style="padding: 1rem; box-shadow: 2px 2px 2px lightgrey;">
    <!-- Logo, here on the left -->
    <section class="navbar-primary">
        <a href="/BacklogWeb" class="navbar-brand"><img src="asset/img/favicon-256.png" width="32" height="32" alt=""></a>
    </section>
    <section class="navbar-section">
        <c:choose>
            <c:when test="${currentUser == null}">
                <a href="/BacklogWeb/LoginServlet" class="btn btn-success">Sign In</a>
            </c:when>
            <c:otherwise>
                <a href="/BacklogWeb" class="btn btn-link"><i class="icon icon-2x icon-people"></i>  <span class="text-large" style="margin-left: 0.5rem;vertical-align: super;">${sessionScope.currentUser.username}</span> </a>
                <form class="m-0" action="LogoutServlet" method="post">
                    <button class="btn btn-link"><i class="icon icon-2x icon-shutdown"></i></button>
                </form>
            </c:otherwise>
        </c:choose>
    </section>
</header>

<div class="login">
    <div class="container grid-lg">
        <div class="column col-10">
            <c:if test="${error != null}">
                <div class="toast toast-error">
                    <button class="btn btn-clear float-right"></button>
                        ${error}
                </div>
            </c:if>
            <h2>Ajouter au backlog</h2>
            <!-- form input control -->
            <div class="form-group">
                <form action="AddNewBacklogServlet" method="post">
                    <input type="hidden" name="backlog_id" value="${backlog_id}">
                    <label class="form-label" for="name">Name</label>
                    <input class="form-input" name="name" type="text" id="name" placeholder="Name">
                    <label class="form-label" for="priority" >Priorité</label>
                    <input class="form-input" id="priority" name="priority" type="number">
                    <label class="form-label" for="estimation">Estimation</label>
                    <input class="form-input" id="estimation" name="estimation" type="number">
                    <label class="form-label" for="textarea">Description</label>
                    <textarea class="form-input" id="textarea" name="description" placeholder="Textarea" rows="3"></textarea>
                    <button class="btn btn-primary button" type="submit">Ajouter</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
