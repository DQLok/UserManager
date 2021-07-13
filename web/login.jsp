<%-- 
    Document   : login
    Created on : Jun 5, 2021, 11:04:37 AM
    Author     : test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome Page</h1>
        <form action="login">
            <p style="color: red">${requestScope.ERRORLOGIN}</p>
            UserID <input type="text" name="userID" value="" required=""/>
            Password <input type="password" name="password" value="" required=""/>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
