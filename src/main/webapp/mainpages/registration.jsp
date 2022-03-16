<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: topch
  Date: 16.03.2022
  Time: 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String name = "main";
    ResourceBundle bundle = ResourceBundle.getBundle(name);
%>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/authorization_menu.css">
    <link type="image/x-icon" href="../resources/img/ico/Logo.svg" rel="icon">
    <title><%= bundle.getString("bank_name") %> authorization</title>
</head>
<body>

<div class="wrapper">
    <main class="page">
        <div class="registration-cssave">
            <form action="/" method="post">
                <h3 class="text-center">Authorization</h3>
                <div class="form-group">
                    <input class="form-control item" type="email" name="email" id="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input class="form-control item" type="password" name="Password" minlength="6" id="password" placeholder="Пароль" required>
                </div>
                <div class="form-group">
                    <a href="#">Forgot password</a>
                    <div class="not_register">Not registered yet? <a href="#">SIGN UP</a></div>
                    <button class="btn btn-primary btn-block create-account" type="submit">Log in</button>
                </div>
            </form>
        </div>
    </main>>
</div>

</body>
</html>
