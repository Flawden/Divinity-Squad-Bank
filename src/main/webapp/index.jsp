<%@ page import="java.util.ResourceBundle" %>

<%  String mainNameSpace = "localization/en/main";
    ResourceBundle bundle = ResourceBundle.getBundle(mainNameSpace);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="resources/css/style.css">
    <link type="image/x-icon" href="resources/img/ico/Logo.svg" rel="icon">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= bundle.getString("bank_name") %></title>
</head>

<body>

<div class="wrapper">
    <main class="page">

        <!-- Хедер - начало -->

        <header class="header">

            <div class="header__row">

                <nav class="header__menu menu">
                    <div class="menu__icon icon-menu">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>

                    <div class="menu__body">
                        <ul class="menu__list">
                            <li class="menu__link"><a href=""><%= bundle.getString("first_header_element") %></a></li>
                            <li class="menu__link"><a href=""><%= bundle.getString("second_header_element") %></a></li>
                            <li class="menu__link"><a href="${pageContext.request.contextPath}/users"><%= bundle.getString("third_header_element") %></a></li>
                            <li class="menu__link"><a href="${pageContext.request.contextPath}/users/new"><%= bundle.getString("fourth_header_element") %></a></li>
                        </ul>
                    </div>
                </nav>

                <div class="header__logo">
                    <img src="resources/img/ico/Logo.svg" alt="">
                </div>

            </div>

        </header>

        <!-- Хедер - конец -->

        <div class="main-screen">
            <div class="main-screen__bg ibg">
                <img src="resources/img/bg_main.png" alt="">
            </div>
        </div>
    </main>
</div>

<!-- Футер - начало -->

<!-- @import "footer.html" -->

<!-- Футер - конец -->

</main>
</div>

<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/Script.js"></script>

</body>
</html>