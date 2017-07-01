<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en" xmlns:spring="http://java.sun.com/xml/ns/javaee" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <title>Авторизация</title>
  <link href="resources/css/bootstrap3/bootstrap.css" rel="stylesheet"/>
  <link href="resources/css/signin.css" rel="stylesheet"/>
</head>
<body>
<div class="container">

  <form class="form-signin" name='f' action="login" method='POST'>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <h2 class="form-signin-heading">Пожалуйста, авторизуйтесь</h2>
    <label for="j_username" class="sr-only">Логин</label>
    <input id="j_username" name="username" class="form-control" placeholder="Email address" required="" autofocus="" type="text">
    <label for="j_password" class="sr-only">Пароль</label>
    <input id="j_password" name="password" class="form-control" placeholder="Password" required="" type="password">
    <!--<div class="checkbox">-->
    <!--<label>-->
    <!--<input value="remember-me" name="_spring_security_remember_me" type="checkbox"> Запомнить меня-->
    <!--</label>-->
    <!--</div>-->
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>

</div>
<script>
  $('.navbar').hide()
</script>
</body>
</html>