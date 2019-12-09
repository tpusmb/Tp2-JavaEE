<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier une entrée du backlog</title>
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
        <a href="/BigFatWeb" class="navbar-brand"><img src="asset/img/favicon-256.png" width="32" height="32" alt=""></a>
    </section>
    <section class="navbar-section">
        <c:choose>
            <c:when test="${currentUser == null}">
                <a href="/BigFatWeb/LoginServlet" class="btn btn-success">Sign In</a>
            </c:when>
            <c:otherwise>
                <a href="/BigFatWeb" class="btn btn-link"><i class="icon icon-2x icon-people"></i>  <span class="text-large" style="margin-left: 0.5rem;vertical-align: super;">${sessionScope.currentUser.username}</span> </a>
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
            <h2>Modifier une entrée</h2>
            <!-- form input control -->
            <div class="form-group">
                <form action="ModifyBacklogEntryServlet" method="post">
                    <input type="hidden" name="entry_id" value="${entry.id}">
                    <label class="form-label" for="name">Nom</label>
                    <input class="form-input" name="name" type="text" id="name" placeholder="Name" value="${entry.name}">
                    <label class="form-label" for="priority" >Priorité</label>
                    <input class="form-input" id="priority" name="priority" type="number" value="${entry.priority}">
                    <label class="form-label" for="estimation">Estimation</label>
                    <input class="form-input" id="estimation" name="estimation" type="number" value="${entry.estimation}">
                    <label class="form-label" for="textarea">Description</label>
                    <textarea class="form-input" id="textarea" name="description" placeholder="Textarea" rows="3">${entry.description}</textarea>
                    <button class="btn btn-primary button" type="submit">Edit</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
