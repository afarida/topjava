<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="tg">
    <tr>
        <th width="100">Описание</th>
        <th width="100">Калории</th>
        <th width="100">Дата и время</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <c:set var="color" value="${meal.exceed?'red':'lightgreen'}"/>
        <tr style="color: ${color}">
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}"/>
                <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime"
                               type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
            </td>
            <td width="50"><a href="meals?action=edit&id=<c:url value="${meal.id}"/>">Редактировать</a></td>
            <td width="50"><a href="meals?action=delete&id=<c:url value="${meal.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="meals?action=insert">Добавить</a></p>
</body>
</html>
