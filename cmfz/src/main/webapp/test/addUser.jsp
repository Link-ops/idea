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
<form action="${pageContext.request.contextPath}/user/regist" method="post">
    name:<input name="name" type="text">
    phone:<input name="phone" type="text">
    password:<input name="password" type="text">
    province:<input name="province" type="text">
    sex:<input name="sex" type="text">
    <button type="submit" value="提交"></button>
</form>
</body>
</html>