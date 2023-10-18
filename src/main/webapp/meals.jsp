<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <jsp:useBean id="id" class="ru.javawebinar.topjava.web.SecurityUtil" scope="request"/>
    <h2>Meals for User#${id.authUserId}</h2>
    <br>
    <table border="=1" cellspacing="0" cellpadding="8">
        <form method="get" action="meals">
            <input type="hidden" name="action" value="filter">
            <th><label for="startDate">Start Date:</label>
                <input type="date" id="startDate" value="${param.startDate}" name="startDate"></th>
            <th><label for="endDate">End Date:</label>
                <input type="date" id="endDate" value="${param.endDate}" name="endDate"></th>
            <th><label for="startTime">Start Time:</label>
                <input type="time" id="startTime" value="${param.startTime}" name="startTime"></th>
            <th><label for="endTime">End Time:</label>
                <input type="time" id="endTime" value="${param.endTime}" name="endTime"></th>
            <th>
                <input type="hidden" name="action" value="filter">
                <input type="submit" value="Filter">
            </th>
        </form>
    </table>


    <br><br>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>