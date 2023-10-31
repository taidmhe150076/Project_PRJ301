<%-- 
    Document   : Login
    Created on : Sep 21, 2023, 11:26:23 PM
    Author     : taisk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="loginBox"> <img class="user" src="https://i.ibb.co/yVGxFPR/2.png" height="100px" width="100px">
            <h3>Forget PassWord</h3>
            <c:if test="${requestScope.flag != true}">
                <form action="forgetPass" method="post">
                    <div class="inputBox">
                        <input id="uname" type="text" name="email" placeholder="email"> 
                        <input id="pass" type="password" name="password" placeholder="Password"> 
                        <input id="pass" type="password" name="cPassword" placeholder="ConfirmPassword"> 
                    </div>
                    <input type="submit" name="" value="Submit">
                </form> 
            </c:if>
            <c:if test="${requestScope.flag == true}">
                <form action="forgetPass" method="post">
                    <div class="inputBox">
                        <input id="pass" type="text" name="otp" placeholder="OTP"> 
                    </div>
                    <input type="submit" name="" value="Submit">
                </form> 
            </c:if>
            <p style="color: red;text-align: center">${requestScope.msg}</p>
            <a href="Login">Log-in<br> </a>
        </div>
    </body>
</html>