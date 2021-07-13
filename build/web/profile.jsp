<%-- 
    Document   : profile
    Created on : Jun 5, 2021, 10:56:57 AM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER eq null || sessionScope.USER ne null && sessionScope.USER.getRoleDTO().getRoleID() eq 'r1'}">
    <c:redirect url="logout"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
    </head>
    <body>
        <h1>Welcome Page</h1>
        <h3>Hi,<a href="viewdayspromotion">${sessionScope.USER.username}</a>
            <a href="logout">logout</a>
        </h3>
        <table border="1">
            <thead>
                <tr>
                    <th>UserName</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Photo</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="user" value="${sessionScope.USER}"/>
                <c:set var="error" value="${requestScope.ERROR}"/>
                <tr>
            <form action="update" enctype="multipart/form-data" method="POST">                
                <td>
                    <input type="text" name="username" value="${user.username}" /><br/>
                    <input type="hidden" name="userID" value="${user.userID}" />
                    <div style="color: red">${error.usernameER}</div>
                </td>
                <td>
                    <input type="text" name="password" value="" /><br/>                            
                    <div style="color: red">${error.passwordER}</div>
                    <input type="hidden" name="passwordsql" value="${user.password}" />
                </td>
                <td>
                    <input type="text" name="email" value="${user.email}" /><br/>                            
                    <div style="color: red">${error.emailER}</div>
                </td>
                <td>
                    <input type="text" name="phone" value="${user.phone}" /><br/>                            
                    <div style="color: red">${error.phoneER}</div>
                </td>
                <td>
                    <img src='<c:url value="/image/${user.photo}"/>' style="height: 100px; width: 100px"/>
                        <input type="file" name="photo"/><br/>
                        <input type="hidden" name="photoinsql" value="${list.photo}" />                                                          
                </td>
                <td>
                    ${user.getRoleDTO().getRolename()}
                    <input type="hidden" name="roleID" value="${user.getRoleDTO().getRoleID()}"/>
                    <input type="hidden" name="rolenameOfuser" value="${user.getRoleDTO().getRolename()}" />
                </td>
                <td>
                    ${user.getStatusDTO().getStatusname()}
                    <input type="hidden" name="statusID" value="${user.getStatusDTO().getStatusID()}" />
                    <input type="hidden" name="statusname" value="${user.getStatusDTO().getStatusname()}" />
                </td>
                <td>
                    <input type="submit" value="Update" name="btAction"/>
                    <input type="hidden" name="txtSearchValue" value="" />
                    <input type="hidden" name="rolename" value="profile" />
                </td>
                <c:choose>
                <c:when test="${requestScope.SUCCESS ne null}">
                    <td style="color: green">
                        ${requestScope.SUCCESS}
                    </td>
                </c:when>
                <c:when test="${requestScope.ERROR ne null}">
                    <td style="color: red">
                        ${requestScope.ERRORPRO}
                    </td>
                </c:when>
            </c:choose>
            </form>
        </tr>
    </tbody>
</table>
</body>
</html>
