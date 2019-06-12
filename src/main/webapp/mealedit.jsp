<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Редактор еды</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Редактор еды</h2>
<form method="POST" action="meals">
    <table>
        <tr>
            </td>
            <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
            <td><input type='hidden' name='id' value="${meal.id}"/></td>
        </tr>
        <tr>
            <td>Date/Time :</td>
            <td><input type=datetime-local value="${meal.dateTime}" name="date"></td>
        </tr>
        <tr>
            <td>Description :</td>
            <td><input type='text' name="description" value="${meal.description}"/></td>
        </tr>
        <tr>
            <td>Calories :</td>
            <td><input type='text' name="calories" value='${meal.calories}'/></td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Edit Save"/></td>
        </tr>
    </table>
</form>
</body>
</html>