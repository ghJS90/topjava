<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Meal</title>
</head>

<body>
<h2>Add Meal</h2>
<hr>

<section>
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <form method="post" action="meals">
        DateTime:
        <label>
            <input type="datetime-local" name="dateTime"
                   value="${meal.dateTime}" placeholder="${meal.dateTime}"> <br>
        </label>

        Descriptions:
        <label>
            <input type="text" name="description"
                   value="${meal.description}" placeholder="${meal.description}"> <br>
        </label>

        Calories:
        <label>
            <input type="number" name="calories"
                   value="${meal.calories}" placeholder="${meal.calories}">
        </label><br>
        <label>
            <input type="number" name="id"
                   value="${meal.id}" placeholder="${meal.id}" hidden>
        </label><br>

        <input type="submit" value="Save">
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>

</section>
</body>
</html>
