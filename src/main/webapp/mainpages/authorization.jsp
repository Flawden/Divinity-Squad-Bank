<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: topch
  Date: 16.03.2022
  Time: 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String authNameSpace = "en/auth";
    String mainNameSpace = "en/main";
    ResourceBundle bundle = ResourceBundle.getBundle(authNameSpace);
    ResourceBundle mainbundle = ResourceBundle.getBundle(mainNameSpace);
%>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/authorization_menu.css">
    <link type="image/x-icon" href="../resources/img/ico/Logo.svg" rel="icon">
    <title><%= mainbundle.getString("bank_name") %> <%= bundle.getString("authorization") %></title>
</head>
<body>

<div class="wrapper">
    <main class="page">
        <div class="registration-cssave">
            <form action="/" method="post">
                <h3 class="text-center"><%= bundle.getString("log_in") %></h3>
                <div class="form-group">
                    <input class="form-control item" type="email" name="email" id="email" placeholder="<%= bundle.getString("email") %>" required>
                </div>
                <div class="form-group">
                    <input class="form-control item" type="password" name="Password" minlength="6" id="password" placeholder="<%= bundle.getString("password") %>" required>
                </div>
                <div class="form-group">
                    <a href="#"><%= bundle.getString("forgot_password") %></a>
                    <div class="not_register">Not registered yet? <a href="../mainpages/registration.jsp"><%= bundle.getString("sign_up") %></a></div>
                    <button class="btn btn-primary btn-block create-account" type="submit"><%= bundle.getString("log_in") %></button>
                </div>
            </form>
        </div>
    </main>>
</div>

</body>
</html>
