<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/user/login">
        手机号<input type="text" name="phone">
        密码<input type="password" name="password">
        <button type="submit"></button>
    </form>
</body>
</html>