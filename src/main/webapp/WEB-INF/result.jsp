<%@ page import="ru.javarush.quest.enums.ResultParam" %>
<%@ page import="ru.javarush.quest.enums.UserReaction" %><%--
  Created by IntelliJ IDEA.
  User: username
  Date: 20.11.2022
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src=https://code.jquery.com/jquery-3.6.0.min.js></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap.js">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <title>Результат</title>
</head>
<body>
<h5>Результат: ${userPoint} баллов</h5>
<c:set var="GREATE_RESULT" value="<%=ResultParam.GREATE_RESULT%>"/>
<c:set var="MIDDLE_RESULT" value="<%=ResultParam.MIDDLE_RESULT%>"/>
<c:set var="BAD_RESULT" value="<%=ResultParam.BAD_RESULT%>"/>
<c:if test="${questResult==GREATE_RESULT}">
    <p>Поздравляем, отличный результат!
        <br>Ты настоящий киноман!</p>
</c:if>
<c:if test="${questResult==MIDDLE_RESULT}">
    <p>Хороший результат, но есть куда стремиться!</p>
</c:if>
<c:if test="${questResult==BAD_RESULT}">
    <p>Не расстраивайся, смотри больше фильмов и попробуй еще раз!</p>
</c:if>

<br>
<c:set var="USER_REACTION_NO" value="<%=UserReaction.NO.getDescription()%>"/>
<c:set var="USER_REACTION_YES" value="<%=UserReaction.YES.getDescription()%>"/>
<form action="${pageContext.request.contextPath}/result" method="post">
    <h6>Хочешь сыграть еще раз?</h6>
    <input type="submit" class="btn btn-primary" name="userReaction"
           value="<%=UserReaction.YES.getNameParam()%>">
    <input type="submit" class="btn btn-primary" name="userReaction"
           value="<%=UserReaction.NO.getNameParam()%>">
</form>

</script>
</body>
</html>
