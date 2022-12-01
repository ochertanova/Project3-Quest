<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: username
  Date: 19.11.2022
  Time: 2:00
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <script src=https://code.jquery.com/jquery-3.6.0.min.js></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap.js">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <title>Вопрос: ${currentQuestion.getId()}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/question" method="post">
    <h5> ${currentQuestion.getId()} ${currentQuestion.getText()}</h5>

    <c:forEach items="${currentAnswers}" var="answer">
        <div class="form-check">
            <input type="radio" class="form-check-input" onchange="updateButtonState()" name="answerId"
                   id="${answer.getId()}" value="${answer.getId()}">
            <label class="form-check-label" for="${answer.getId()}">
                    ${answer.getText()}
            </label>
        </div>
    </c:forEach>
    <div>
        <button type="submit" class="btn btn-primary" disabled>Следующий вопрос</button>
    </div>
    <script>
        function updateButtonState() {
            let submitButton = document.querySelector('button[type="submit"]');
            submitButton.disabled = false;
        }
    </script>
</form>
</body>
</html>
