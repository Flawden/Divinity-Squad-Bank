<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: topch
  Date: 16.03.2022
  Time: 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String registrationNameSpace = "en/reg";
    String mainNameSpace = "en/main";
    ResourceBundle bundle = ResourceBundle.getBundle(registrationNameSpace);
    ResourceBundle mainbundle = ResourceBundle.getBundle(mainNameSpace);
%>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/authorization_menu.css">
    <link rel="stylesheet" href="../resources/css/registration_manu.css">
    <link type="image/x-icon" href="../resources/img/ico/Logo.svg" rel="icon">
    <title><%= mainbundle.getString("bank_name") %> authorization</title>
</head>
<body>

<div class="wrapper">
    <main class="page">
        <div class="registration-cssave">
            <form action="/" method="post">
                <h3 class="text-center"><%= bundle.getString("sign_up") %></h3>
                <div class="form-group">
                    <input class="form-control item" type="text" name="name" id="name" placeholder="<%= bundle.getString("name") %>" required>
                </div>
                <div class="form-group">
                    <input class="form-control item" type="text" name="lastname" id="lastname" placeholder="<%= bundle.getString("lastname") %>" required>
                </div>
                <select class="form-control item" size="1" name="gender" id="gender">
                    <option disabled><%= bundle.getString("select_gender") %>:</option>
                    <option value="male"><%= bundle.getString("male") %></option>
                    <option selected value="female"><%= bundle.getString("female") %></option>
                    <option value="idk"><%= bundle.getString("idk") %></option>
                    <option value="helicopter"><%= bundle.getString("attack_helicopter") %></option>
                </select></p>
                <div class="form-group">
                    <input class="form-control item" type="date" name="birdthdate" id="birdthdate" placeholder="<%= bundle.getString("birthdate") %>" required>
                </div>
                <div class="form-group">
                    <input class="form-control item" type="email" name="email" id="email" placeholder="<%= bundle.getString("email") %>" required>
                </div>
                <div class="form-group">
                    <input class="form-control item" type="password" name="Password" minlength="6" id="password" placeholder="<%= bundle.getString("password") %>" required>
                </div>
                <div class="form-group">
                    <div class="not_register">Already registered? <a href="../mainpages/authorization.jsp"><%= bundle.getString("big_log_in") %></a></div>
                    <button class="btn btn-primary btn-block create-account" type="submit"><%= bundle.getString("log_in") %></button>
                </div>
            </form>
        </div>
    </main>>
</div>

</body>
</html>
