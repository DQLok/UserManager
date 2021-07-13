<%-- 
    Document   : search
    Created on : Jun 1, 2021, 1:53:43 PM
    Author     : test
--%>

<%@page import="locdq.users.UsersDTO"%>
<%@page import="locdq.roles.RolesDTO"%>
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
        <h1>Welcome Page</h1>
        <h3>Hi Admin, <a href="viewdayspromotion">${sessionScope.USER.username}</a>            
            <a href="logout">logout</a>
        </h3>
        <p><a href="insertpage">insert</a></p>
        <p><a href="promotionpage">promotion</a></p>
        <p style="color: green">${requestScope.SUCCESSDELETE}</p>
        <form action="search">
            Search Value <input type="text" name="txtSearchValue" value="${requestScope.SEARCH_VALUE}" />
            <input type="submit" value="Search" name="btAction" /><br/>
            <input type="hidden" name="rolename" value="${requestScope.ROLE eq null ? 'All': requestScope.ROLE}" />
            <p>
        </form>
        <form action="search">
            <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="All" name="rolename"/>
            <c:forEach var="roles" items="${sessionScope.LIST_ROLES}">
                <input type="submit" value="${roles.rolename}" name="rolename"/>
            </c:forEach>
        </form>
    </p>

    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>UserName</th>
                <th>Password</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Photo</th>
                <th>RoleName</th>
                <th>StatusName</th>
                <th>Update</th>                
                <th>Delete</th>
                <th>Add promotionlist</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="list" items="${sessionScope.LIST_USERS}" varStatus="counters">
                <c:if test="${requestScope.USERID eq list.userID}" >
                    <c:set var="error" value="${requestScope.ERROR}"/>
                </c:if>
            <form action="update" enctype="multipart/form-data" method="POST">
                <tr>
                    <td>${counters.count}</td>
                    <td>                            
                        <input type="text" name="username" value="${list.username}" /><br/>
                        <input type="hidden" name="userID" value="${list.userID}" />
                        <div style="color: red">${error.usernameER}</div>
                    </td>
                    <td>
                        <input type="text" name="password" value="" /><br/>                            
                        <div style="color: red">${error.passwordER}</div>
                        <input type="hidden" name="passwordsql" value="${list.password}" />
                    </td>
                    <td>
                        <input type="text" name="email" value="${list.email}" /><br/>                            
                        <div style="color: red">${error.emailER}</div>
                    </td>
                    <td>
                        <input type="text" name="phone" value="${list.phone}" /><br/>                            
                        <div style="color: red">${error.phoneER}</div>
                    </td>
                    <td>
                        <img src='<c:url value="/image/${list.photo}"/>' style="height: 100px; width: 100px"/>
                        <input type="file" name="photo"/><br/>
                        <input type="hidden" name="photoinsql" value="${list.photo}" />
                    </td>
                    <td>    
                        <select name="roleID">
                            <c:forEach var="item" items="${sessionScope.LIST_ROLES}">
                                <option value="${item.roleID}" ${item.roleID eq list.getRoleDTO().getRoleID() ? "selected" :""}>${item.rolename}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="rolenameOfuser" value="${list.getRoleDTO().getRolename()}" />
                    </td>
                    <td>
                        ${list.getStatusDTO().getStatusname()}
                        <input type="hidden" name="statusID" value="${list.getStatusDTO().getStatusID()}" />
                        <input type="hidden" name="statusname" value="${list.getStatusDTO().getStatusname()}" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${list.getRoleDTO().getRoleID() eq 'r1' && sessionScope.USER.userID ne list.userID || list.getStatusDTO().getStatusID() eq 's2'}">
                                <input type="submit" value="Update" name="btAction" disabled=""/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Update" name="btAction"/>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="txtSearchValue" value="${requestScope.SEARCH_VALUE eq param.txtSearchValue ? requestScope.SEARCH_VALUE :""}" />
                        <input type="hidden" name="rolename" value="${requestScope.ROLE eq param.rolename ? requestScope.ROLE : ""}" />
                    </td>
            </form>
            <td>
                <form action="delete">
                    <c:choose>
                        <c:when test="${list.getRoleDTO().getRoleID() eq 'r1' || list.getStatusDTO().getStatusID() eq 's2'}">
                            <input type="submit" value="delete" name="btAction" disabled=""/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="delete" name="btAction"/>
                        </c:otherwise>
                    </c:choose>                    
                    <input type="hidden" name="userID" value="${list.userID}" />
                    <input type="hidden" name="txtSearchValue" value="${requestScope.SEARCH_VALUE eq param.txtSearchValue ? requestScope.SEARCH_VALUE :""}" />
                    <input type="hidden" name="rolename" value="${requestScope.ROLE eq param.rolename ? requestScope.ROLE : ""}" />
                </form>
            </td>            
            <td>
                <form action="addpromotion">
                    <c:choose>
                            <c:when test="${list.getRoleDTO().getRoleID() eq 'r1' || list.getStatusDTO().getStatusID() eq 's2'}">
                                <input type="submit" value="Add" name="btAction" disabled=""/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Add" name="btAction"/>
                            </c:otherwise>
                        </c:choose>
                    
                    <input type="hidden" name="userID" value="${list.userID}"/>
                    <input type="hidden" name="txtSearchValue" value="${requestScope.SEARCH_VALUE eq param.txtSearchValue ? requestScope.SEARCH_VALUE :""}" />
                    <input type="hidden" name="rolename" value="${requestScope.ROLE eq param.rolename ? requestScope.ROLE : ""}" />
                </form>                    
            </td>
            <c:choose>
                <c:when test="${requestScope.SUCCESS ne null && list.userID eq USERID}">
                    <td style="color: green">
                        ${requestScope.SUCCESS}
                    </td>
                </c:when>
                <c:when test="${requestScope.ERRORADD ne null && list.userID eq USERID}">
                    <td style="color: red">
                        ${requestScope.ERRORADD}
                    </td>
                </c:when>
            </c:choose>
        </tr>

    </c:forEach>
</tbody>
</table>


</body>
</html>
