<%--
  Created by IntelliJ IDEA.
  User: tovnah
  Date: 01.07.17
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns:spring="http://java.sun.com/xml/ns/javaee" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <title></title>

  <link href="resources/css/bootstrap3/bootstrap.min.css" rel="stylesheet"/>
  <link href="resources/css/all.css" rel="stylesheet"/>
  <link href="resources/css/bootstrap3/bootstrap-theme.min.css" rel="stylesheet"/>

  <script src="resources/js/controllers/addNewDvdCtrl.js"></script>
  <script src="resources/js/services/profileService.js"></script>
  <script src="resources/js/services/dvdService.js"></script>
  <script src="resources/js/controllers/takenByMeCtrl.js"></script>
  <script src="resources/js/controllers/takenFromMeCtrl.js"></script>
  <script src="resources/js/controllers/freeDvdCtrl.js"></script>
  <script src="resources/js/controllers/mainMenuCtrl.js"></script>

  <script src="resources/js/lib/angular.min.js"></script>
  <script src="resources/js/lib/angular-route.min.js"></script>

  <script src="resources/js/app.js"></script>
  <script src="resources/js/directives/freeDvdList.js"></script>
  <script src="resources/js/directives/givenDvdList.js"></script>
  <script src="resources/js/directives/messageDialog.js"></script>
  <script src="resources/js/directives/sessionInjector.js"></script>
  <script src="resources/js/directives/takenDvdList.js"></script>
  <script src="resources/js/directives/validFile.js"></script>
  <script src="resources/js/directives/waiter.js"></script>
  <script src="resources/js/directives/userList.js"></script>

  <script src="resources/js/lib/jquery-3.2.1.min.js"></script>

  <script src="resources/js/lib/tether.min.js"></script>
  <script src="resources/js/lib/bootstrap3/bootstrap.min.js"></script>
</head>
<body ng-app="app">

<div class="container">
  <!-- Navigation -->
  <nav class="navbar navbar-default" ng-controller="MainMenuCtrl">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#!/">DVDExchanger</a>
      </div>
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li ng-class="menuClass('')">
            <a href="#!/">Свободные DVD</a>
          </li>
          <li ng-class="menuClass('new')">
            <a href="#!/new">Добавление DVD</a>
          </li>

          <li ng-class="menuClass('takenByMe')">
            <a href="#!/takenByMe">Взятые мной</a>
          </li>

          <li ng-class="menuClass('takenFromMe')">
            <a href="#!/takenFromMe">Взятые у меня</a>
          </li>
          <li>
            <a href="logout">Выход</a>
          </li>

        </ul>
      </div>
    </div>

  </nav>

  <div class="panel panel-default"  ng-view>

  </div>
  <div>
    <message-dialog/>
  </div>

  <div>
    <waiter/>
  </div>
</div>

</body>

</html>