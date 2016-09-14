<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <th width="100">Description</th>
        <th width="100">Calories</th>
        <th width="100">DateTime</th>
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
        </tr>
    </c:forEach>
</table>
</body>
</html>
