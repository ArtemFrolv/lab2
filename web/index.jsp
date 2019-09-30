<%@ page import="servlets.PointCoordinates" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    HttpSession history = request.getSession(true);
    String historyString = (String) history.getAttribute("coord");
    if (historyString == null) {
        historyString = "";
    }
    historyString = historyString.replace('"', '\'');

    ArrayList<PointCoordinates> coordinates = new ArrayList<PointCoordinates>();
    Type type = new TypeToken<ArrayList<PointCoordinates>>() {
    }.getType();
    Gson gson = new Gson();
    if (history.getAttribute("coord") != null)
        coordinates = gson.fromJson((String) history.getAttribute("coord"), type);
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width; initial-scale=1">
    <link href="css/main.css" rel="stylesheet">
    <title>Лабораторная работа №1</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/valid.js"></script>
    <script src="${pageContext.request.contextPath}/js/history.js"></script>
    <script src="${pageContext.request.contextPath}/js/area.js"></script>
</head>
<body>
<header id="header">
    <div class="header_text">
        Студент: Фролов Артём,
        вариант: 201019,
        группа: P3201<br>
    </div>
</header>
<form action="${pageContext.request.contextPath}/area" method="get" id="form" class="flex">
    <main id="main">
        <div class="field_in">
            <div class="message">
                Заполните поля для проверки
            </div>
            <div class="r_field">
                <div class="charR">
                    R=
                </div>
                <input class="r_input" name="R" placeholder="Введите число в интервале от 1 до 4" type="text"
                       title="Введите значение R" id="R" autocomplete="off" onpaste="return false;" maxlength="4">
            </div>
            <div class="y_field">
                <div class="charY">
                    Y=
                </div>
                <input class="y_input" name="Y" placeholder="Введите число в интервале от -3 до 5" type="text"
                       title="Введите значение Y" id="Y" autocomplete="off" onpaste="return false;" maxlength="4">
            </div>
            <div class="help_field">
                <div class="x_field">
                    <div class="charX">
                        X=
                    </div>
                    <select id="X" name="X">
                        <option value="-4" selected>-4</option>
                        <option value="-3">-3</option>
                        <option value="-2">-2</option>
                        <option value="-1">-1</option>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option id="helpX">Другое</option>
                    </select>
                </div>
                <div class="check">
                    <input type="submit" value="Проверить" class="check_box" id="checkBox">
                </div>
            </div>
        </div>
        <div class="img_field">
            <canvas class="canvasGraph" id="canvasGraph" height="200" width="200"></canvas>
            <canvas class="canvas" id="canvas" height="200" width="200" history="<%= historyString%>"></canvas>
        </div>
        <script></script>
    </main>
    <%
        if (!coordinates.isEmpty()) {
            out.println("<ul class=\"table\">" +
                    "<li class=\"line\">" +
                    "<div class=\"item one\">X</div>" +
                    "<div class=\"item two\">Y</div>" +
                    "<div class=\"item three\">R</div>" +
                    "<div class=\"item four\">Lead<br>time<br>(ms)</div>" +
                    "<div class=\"item five\">Current<br>time</div>" +
                    "<div class=\"item six\">Result</div>" +
                    "</li>");

            for (PointCoordinates coordinate : coordinates) {
                out.println("<li class=\"line\">\n" +
                        "<div class=\"item one\">" + coordinate.getX() + "</div>\n" +
                        "<div class=\"item two\">" + coordinate.getY() + "</div>\n" +
                        "<div class=\"item three\">" + coordinate.getR() + "</div>\n" +
                        "<div class=\"item four\">" + coordinate.getTimeExecute() + "</div>\n" +
                        "<div class=\"item five\">" + coordinate.getTime() + "</div>\n" +
                        "<div class=\"item six\">" + coordinate.getCheck() + "</div>\n" +
                        "</li>\n");
            }
            out.print("</ul>");
        }
    %>
</form>
<div class="orehus" id="orehus"></div>
<div class="fruits shake" id="fruits"></div>
<div class="achievement" id="achievement"></div>
</body>
</html>
