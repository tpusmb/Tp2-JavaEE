<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voir les commentaires</title>
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
            <h2 class="text-center">ID: ${entry.id}</h2>
            <p>Nom : ${entry.name}</p>
            <p>Estimation : ${entry.estimation}</p>
            <p>Priorité : ${entry.priority}</p>
            <p>Description : ${entry.description}</p>
            <div class="panel">
                <div class="panel-header">
                    <div class="panel-title">Commentaires</div>
                </div>
                <div class="panel-nav">
                    <!-- navigation components: tabs, breadcrumbs or pagination -->
                </div>
                <div class="panel-body">
                    <c:if test="${fn:length(comments) eq 0}">
                        <div class="empty">
                            <div class="empty-icon">
                                <i class="icon icon-3x icon-message"></i>
                            </div>
                            <p class="empty-title h5">Aucun commentaires</p>
                            <p class="empty-subtitle">Cliquer sur le bouton pour ajouter un commantaire</p>
                            <div class="empty-action">
                                <form action="ViewEntryCommentsServlet" method="post">
                                    <input type="hidden" name="entry_id" value="${entry_id}">
                                    <div class="form-group">
                                        <textarea name="content" id="textarea" cols="60" rows="10"></textarea>
                                    </div>
                                    <button class="btn btn-primary" type="submit">Ajouter un commentaire</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach var="comment"  items="${comments}" >
                        <c:set var = "initial" value = "${fn:substring(comment.author.username, 0, 1)}" />
                        <div class="tile">
                            <div class="tile-icon">
                                <figure class="avatar text-capitalize" data-initial="${initial}"></figure>
                            </div>
                            <div class="tile-content">
                                <p class="tile-title text-bold text-capitalize">${comment.author.username}</p>
                                <p class="tile-subtitle">${comment.content}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="panel-footer">
                    <c:if test="${fn:length(comments) gt 0}">
                        <div class="form-group">
                            <form action="ViewEntryCommentsServlet" method="post">
                                <input type="hidden" name="entry_id" value="${entry_id}">
                                <div class="form-group">
                                    <textarea name="content" id="textarea" cols="60" rows="10"></textarea>
                                </div>
                                <button class="btn btn-primary" type="submit">Ajouter un commentaire</button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
