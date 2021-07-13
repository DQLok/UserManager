<%-- 
    Document   : insert
    Created on : Jun 2, 2021, 11:20:12 PM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER eq null || sessionScope.USER ne null && sessionScope.USER.getRoleDTO().getRoleID() ne 'r1'}">
    <c:redirect url="logout"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insert Page</h1>
        <h3>Welcome,${sessionScope.USER.username}
            <a href="logout">logout</a>
        </h3>
        <p>
            <a href="LoadDataServlet">Again</a>
        </p>
        <div style="color: green">${requestScope.SUCCESS}</div>
        <c:set var="error" value="${requestScope.ERROR}" />
        <form action="insert"  enctype="multipart/form-data" method="POST">        
            UserID <input type="text" name="userID" value="${error.userIDER eq "" ? param.userID : ""}" required=""/><br/>        
            <div style="color: red">${error.userIDER}</div><br/>
            UserName <input type="text" name="username" value="${error.usernameER eq "" ? param.username : ""}" required=""/><br/>        
            <div style="color: red">${error.usernameER}</div><br/>
            Password <input type="password" name="password" value="${error.passwordER eq "" ? param.password : ""}" required=""/><br/>        
            <div style="color: red">${error.passwordER}</div><br/>
            Confirm <input type="password" name="confirm" value="${error.passwordER eq "" ? param.confirm : ""}" required=""/><br/>        
            <div style="color: red">${requestScope.CONFIRM}</div><br/>
            Email <input type="text" name="email" value="${error.emailER eq "" ? param.email : ""}" required=""/><br/>        
            <div style="color: red">${error.emailER}</div><br/>
            Phone <input type="text" name="phone" value="${error.phoneER eq "" ? param.phone : ""}" required=""/><br/>        
            <div style="color: red">${error.phoneER}</div><br/>
            
            Photo <input type="file" name="photo"/><br/>
            <div style="color: red">${error.photoER}</div><br/>
            
            <select name="roleID">
                <c:forEach var="role" items="${sessionScope.LIST_ROLES}">
                    <option value="${role.roleID}">${role.rolename}</option>
                </c:forEach>
            </select><br/>
            <input type="submit" value="Insert" name="btAction"/>
            <input type="reset" value="Reset" />  
        </form>

    </body>
</html>
