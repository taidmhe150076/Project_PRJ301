<%-- 
    Document   : Login
    Created on : Sep 21, 2023, 11:26:23 PM
    Author     : taisk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="loginBox"> <img class="user" src="https://i.ibb.co/yVGxFPR/2.png" height="100px" width="100px">
            <h3>Sign in here</h3>
            <form action="Login" method="post">
                <div class="inputBox">
                    <input id="uname" type="text" name="Username" placeholder="Username"> 
                    <input id="pass" type="password" name="Password" placeholder="Password"> 
                </div>
                    <input type="submit" name="" value="Login">
            </form> 
            <a href="#">Forget Password<br> </a>
            <a href="#">Sign-Up<br> </a>
        </div>
    </body>
</html>