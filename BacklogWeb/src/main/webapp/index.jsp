<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Application de gestion de gestion de backlog</title>
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
                <a href="/BacklogWeb" class="btn btn-link"><i class="icon icon-2x icon-people"></i>  <span class="text-large text-capitalize" style="margin-left: 0.5rem;vertical-align: super;">${sessionScope.currentUser.username}</span> </a>
                <form class="m-0" action="LogoutServlet" method="post">
                    <button class="btn btn-link"><i class="icon icon-2x icon-shutdown"></i></button>
                </form>
            </c:otherwise>
        </c:choose>
    </section>
</header>
<section class="main grid-lg">
    <div class="container">
        <div class="column col-6 p-centered margin-bottom-30">
            <c:if test="${global_notification_success == true}">
                <div class="toast toast-success">
                    <button class="btn btn-clear float-right" onclick="document.getElementsByClassName('toast-success')[0].style.display='none';"></button>
                        ${message}
                </div>
            </c:if>
        </div>
        <div class="column col-6 p-centered">
            <h1 class="text-center">Application de gestion de backlog</h1>
            <c:if test="${currentUser != null}">
                <div>
                    <form action="CreateAgencyServlet" method="post">
                        <label class="form-label" for="name">Name</label>
                        <input class="form-input" name="name" type="text" id="name" placeholder="Name">

                        <button type="submit" class="btn btn-primary button">Ajouter une agence</button>
                    </form>
                </div>
                <div>
                    <form action="FetchBacklogServlet" method="post">
                        <div class="form-group">
                            <label class="form-label" for="select-agency">Sélectionnez une agence</label>
                            <select name="agency_id" class="form-select" id="select-agency">
                                <c:forEach var="agence"  items="${sessionScope.listAgence}" >
                                    <option value="${agence.id}">${agence.name}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-primary button">Voir le backlog</button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
        <div class="column col-6 p-centered">
            <c:if test="${notification_success == true}">
                <div class="toast toast-success">
                    <button class="btn btn-clear float-right" onclick="document.getElementsByClassName('toast-success')[0].style.display='none';"></button>
                        ${message}
                </div>
            </c:if>
        </div>
        <div class="column col-10 p-centered margin-top-30">
            <c:if test="${entries != null}">
            <form action="AddNewBacklogServlet" method="get">
                <input type="hidden" name="backlog_id" value="${backlog_id}">
                <button class="btn btn-primary"><i class="icon icon-plus"></i></button>
            </form>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Identifiant</th>
                    <th>Nom</th>
                    <th>Date de création</th>
                    <th>Priorité</th>
                    <th>Estimation</th>
                    <th>Descriptions</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entry"  items="${entries}" >
                    <tr class="active">
                        <td>${entry.id}</td>
                        <td>${entry.name}</td>
                        <td>${entry.creation}</td>
                        <td>${entry.priority}</td>
                        <td>${entry.estimation}</td>
                        <td>${entry.description}</td>
                        <td>
                            <form class="d-inline-block" action="ViewEntryCommentsServlet" method="get">
                                <input type="hidden" name="entry_id" value="${entry.id}">
                                <button class="btn btn-primary"><i class="icon icon-message"></i></button>
                            </form>
                            <form class="d-inline-block" action="ModifyBacklogEntryServlet" method="get">
                                <input type="hidden" name="entry_id" value="${entry.id}">
                                <button class="btn btn-primary"><i class="icon icon-edit"></i></button>
                            </form>
                            <form class="d-inline-block" action="DeleteBacklogEntryServlet" method="get">
                                <input type="hidden" name="backlog_id" value="${backlog_id}">
                                <input type="hidden" name="entry_id" value="${entry.id}">
                                <button class="btn btn-primary" onclick="return window.confirm('Êtes-vous sûr de vouloir supprimer cette entrée ?');"><i class="icon icon-delete"></i></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:if>
        </div>
    </div>
</section>


</body>

</html>