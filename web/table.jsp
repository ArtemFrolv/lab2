<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="servlets.PointCoordinates" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%
    HttpSession history = request.getSession(true);
    String historyString = (String) history.getAttribute("coord");
    if (historyString == null) {
        historyString = "";
    }
    historyString = historyString.replace('"', '\'');

    ArrayList<PointCoordinates> coordinates;
    Type type = new TypeToken<ArrayList<PointCoordinates>>() {}.getType();
    Gson gson = new Gson();
    coordinates = gson.fromJson((String) history.getAttribute("coord"), type);
%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet">
    <title>Таблица с результатами</title>
    <meta name="viewport" content="width=device-width; initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/area.js"></script>
    <script src="${pageContext.request.contextPath}/js/history.js"></script>
</head>
<body>
<header>
    <div class="header_text">
        Студент: Фролов Артём,
        вариант: 201019,
        группа: P3201<br>
    </div>
    <div>
        <a class="pic" href="${pageContext.request.contextPath}/index.jsp">
            <img class="homeImg" src="${pageContext.request.contextPath}/img/home.png">
        </a>
    </div>
</header>
<div class="flex">
    <main>
        <div class="canvasField">
            <canvas class="canvasGraph" id="canvasGraph" height="200" width="200"></canvas>
            <canvas class="canvas" id="canvas" height="200" width="200" history="<%= historyString %>"></canvas>
        </div>
        <ul class="table">
            <li class="line">
                <div class="item one">X</div>
                <div class="item two">Y</div>
                <div class="item three">R</div>
                <div class="item four">Lead<br>time<br>(ms)</div>
                <div class="item five">Current<br>time</div>
                <div class="item six">Result</div>
            </li>
            <%
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
            %>
        </ul>
    </main>
</div>
<div class="fruits shake" id="fruits">
</div>
</body>
</html>