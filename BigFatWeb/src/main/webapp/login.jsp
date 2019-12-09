<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" type="text/css" href="asset/css/base.css" >
    <link rel="stylesheet" type="text/css" href="asset/css/spectre.min.css">
    <link rel="stylesheet" type="text/css" href="asset/css/spectre-exp.min.css">
    <link rel="stylesheet" type="text/css" href="asset/css/spectre-icons.min.css">
    <link rel="icon" href="favicon.ico" />
</head>
<body>


<div class="login">
    <div class="container grid-lg">
        <div class="column col-10">
        <c:if test="${error != null}">
            <div class="toast toast-error">
                <button class="btn btn-clear float-right"></button>
                    ${error}
            </div>
        </c:if>
        <h2>Sign in</h2>
        <!-- form input control -->
        <div class="form-group">
            <form action="LoginServlet" method="post">
                <label class="form-label" for="username">Name</label>
                <input class="form-input" name="username" type="text" id="username" placeholder="Name">
                <label class="form-label" for="password">Password</label>
                <input class="form-input" name="password" type="password" id="password" placeholder="Password">
                <button class="btn btn-primary button" type="submit">Sign in</button>
            </form>
        </div>
        </div>
    </div>
</div>
</body>
</html>
