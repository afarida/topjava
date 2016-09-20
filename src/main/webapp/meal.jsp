<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Add new meal</title>
</head>
<body>
<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>

<c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}"/>
<fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both"/>
<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" var="dt"/>

<form method="post" action="meals" name="frmAddUpdateUser">
    ID : <input type="text" readonly="readonly" name="id" value="${meal.id}"><br/>
    Дата и время : <input type="text" name="datepicker" value="${dt}"><br/>
    Описание : <input type="text" name="description" value="${meal.description}"><br/>
    Калории : <input type="text" name="calories" value="${meal.calories}"><br/>
    <input type="submit" value="OK"/>
</form>
</body>
</html>
