<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<form action="manager" method="POST">
    <input type="submit" value="Выйти" id="logoutButton">
</form>
<h2>Сгенерировано: ${generationTime}</h2>
<h2>Список файлов и папок в текущей директории: ${currentDirPath}</h2>
<c:url value="manager" var="upUrl">
    <c:param name="path" value="${parentDirPath}"/>
</c:url>
<a href="${upUrl}">Вверх</a>
<style type="text/css">
    BODY {
        background: white; /* Цвет фона веб-страницы */
    }

    TD, TH {
        padding: 3px; /* Поля вокруг содержимого таблицы */
        border: 1px solid maroon; /* Параметры рамки */
        text-align: left; /* Выравнивание по левому краю */
    }
    #logoutButton {
        float: right; /* Выравнивание кнопки вправо */
        width: 120px; /* Ширина кнопки */
        height: 40px;
        font-size: 18px;
    }
</style>
<table>
    <tr>
        <th>Файл</th>
        <th>Ссылка</th>
        <th>Размер</th>
        <th>Дата</th>
    </tr>

    <c:forEach var="listElement" items="${list}">
        <tr>
            <td>${listElement.name}</td>
            <td>
                <c:set var="itemName" value="${listElement.name}" />
                <c:choose>
                    <c:when test="${fn:endsWith(itemName, '/')}">
                        <c:url value="manager" var="downUrl">
                            <c:param name="path" value="${listElement.path}"/>
                        </c:url>
                        <a href="${downUrl}">Перейти</a>
                    </c:when>
                    <c:otherwise>
                        <c:url value="DownloadServlet" var="downloadUrl">
                            <c:param name="path" value="${listElement.path}"/>
                        </c:url>
                        <a href="${downloadUrl}" download>Скачать</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${listElement.size}</td>
            <td>${listElement.date}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
