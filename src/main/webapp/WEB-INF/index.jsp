<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
    <title>Quest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>

<body>
<h1>Квест-игра "Этот дивный мир кино!"</h1>
<hr>
<p>Считаете себя фанатом кино? Что ж это легко проверить.
    <br> Зарегистрируйтесь и ответьте на увлекательные вопросы!</p>
<p>За каждый правильный ответ - <b>1 балл</b></p>
<p>Приятной игры!</p>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <div>
        <label for="name" class="form-label">Имя пользователя:</label>
        <input type="text" class="form-control" id="name" onchange="updateButtonState()" placeholder="Введите ваше имя"
               name="userName">
    </div>
    <button type="submit" class="btn btn-primary" disabled>Начать игру</button>
    <script>
        function updateButtonState() {
            let submitButton = document.querySelector('button[type="submit"]');
            submitButton.disabled = false;
        }
    </script>
    <br><img src="${pageContext.request.contextPath}/image/startPage.jpg">
</form>
</body>
</html>