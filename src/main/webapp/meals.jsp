<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Список еды</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="2">
    <tr>
        <td>Date/Time</td>
        <td>Description</td>
        <td>Calories</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="color: ${meal.excess ? 'red' :  'green'} ;">
            <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"/>
                <fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${meal.description}</td>
            <td> ${meal.calories}</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>