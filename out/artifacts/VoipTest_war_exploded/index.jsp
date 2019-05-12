<%--
  Created by IntelliJ IDEA.
  User: rezaa
  Date: 5/8/2019
  Time: 12:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Push Notifications</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
    <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
<div style="background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: hand;" onclick="send()"> Send Push</div>
</body>
<script>

    var mHttp;
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function ($scope, $http, $interval) {
        mHttp = $http;
    });

    function send() {
        mHttp({
            method: 'GET',
            url: 'SendPushToClients',
        }).then(function mySuccess(response) {
            if (response.status == 200) {
            } else if (response.status == 408) {
                //$("#infoForm").css("display", "none");
            }
        }, function myError(response) {

        });
    }
</script>
</html>
