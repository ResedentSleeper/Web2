<%@ page import="server.Dot" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="dotbean" class="server.JavaBean" scope="session"/>

<html>
<head>
    <title>Результат проверки</title>
    <link rel="stylesheet" href="css/table.css">
    <meta charset="utf-8">
</head>

<body>
<table>
    <tr>
        <th colspan="4">
            <%--
                Я знаю что так нельзя, но это было первый и последний раз, честно
                Ну прямо самое честное слово, которое я могу дать
                ...
                Ладно, на сдаче выполнения мы можем это просто убрать

             --%>
            <% if (dotbean.getLastHit() == null) {%>
            <p>There's a storm coming</p><br>
            <img class="res" src="css/img/gif/s.gif">
            <%
            } else {
                if (dotbean.getLastHit()) {
            %>
            <p>YES YES YES</p><br>
            <img class="res" src="css/img/gif/yes2.gif">
            <% } else {%>
            <p>NO(( </p><br>
            <img class="res" src="css/img/gif/NoB.gif">
            <% }
            } %>

        </th>
    </tr>
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th><b>Попадание/Промах</b></th>
    </tr>
    <%
        List<Dot> list = dotbean.getDots();
        for (Dot dot : list) {
    %>
    <tr>
        <td>
            <%=dot.getX() %>
        </td>
        <td><%=dot.getY() %>
        </td>
        <td><%=dot.getR()%>
        </td>
        <td><%=dot.isHit() ? "Попадание" : "Промах" %>
        </td>

        <% if (list.get(0).getR() == dot.getR()) { %>
        <script>
            parent.markPoint(<%=dot.getX() %>, <%=dot.getY() %>, <%=dot.getR() %>, <%=dot.isHit()   %>)
        </script>
        <% } %>
    </tr>
    <%}%>

</table>
</body>
</html>