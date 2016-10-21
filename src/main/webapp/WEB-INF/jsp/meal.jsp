<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form:form method="post" action="${pageContext.request.contextPath}/meals/save" commandName="meal">
        <form:hidden path="id"/>
        <dl>
            <dt>DateTime:</dt>
            <dd><form:input path="dateTime"/></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><form:input size="40" path="description"/></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><form:input path="calories"/></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form:form>
</section>
</body>
</html>
