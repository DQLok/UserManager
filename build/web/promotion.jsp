<%-- 
    Document   : promotion
    Created on : Jun 5, 2021, 10:18:20 AM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER eq null || sessionScope.USER ne null && sessionScope.USER.getRoleDTO().getRoleID() ne 'r1'}">
    <c:redirect url="logout"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion Page</title>
    </head>
    <body>
        <h3>Welcome, <a href="viewdayspromotion">${sessionScope.USER.username}</a>            
            <a href="logout">logout</a>
        </h3>
        <p>
            <a href="loaddata">Again</a>
        </p>
        <p style="color: green">
            ${requestScope.SUCCESS}
        </p>
        ${requestScope.ALO}
        <form action="crudpromotion">
            <c:if test="${sessionScope.PROMOTIONLIST.size() ne 0 && sessionScope.PROMOTIONLIST ne null}">
                <p>
                    <input type="submit" value="Save" name="btAction"/>
                </p>
            </c:if>                    
            <p>
                <select name="typepro">
                    <c:forEach var="type" items="${sessionScope.LIST_TYPEPROMOTIONS}">
                        <option value="${type.typeID}">${type.typename}</option>
                    </c:forEach>
                </select>
            </p>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>User Name</th>
                    <th>Rank</th>
                    <th>Assignment Date</th>
                    <th>Remove</th>
                    <th>Update Rank</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="promotions" items="${sessionScope.PROMOTIONLIST}" varStatus="counter">
                    <tr>
                <form action="crudpromotion"> 
                    <td>
                        ${counter.count}
                        <input type="hidden" name="index" value="${counter.count}" />
                    </td>
                    <td>
                        ${promotions.getUsersDTO().getUsername()}
                        <input type="hidden" name="userID" value="${promotions.getUsersDTO().getUserID()}"/>
                    </td>
                    <td>
                        <input type="text" name="rank" value="${promotions.rank}" />
                    </td>
                    <td>
                        ${promotions.assignmentdate}
                        <input type="hidden" name="assignmentdate" value="${promotions.assignmentdate}" />
                    </td>
                    <td>                                                       
                        <input type="submit" value="Remove" name="btAction"/>
                    </td>
                    <td>
                        <input type="submit" value="UpdateRank" name="btAction"/>
                    </td>
                </form>                        
                <c:choose>
                    <c:when test="${requestScope.ERROR ne null && requestScope.INDEX eq counter.count}">
                        <td style="color: red">
                            ${requestScope.ERROR}
                        </td>
                    </c:when>
                    <c:when test="${requestScope.SUCCESS ne null && requestScope.INDEX eq counter.count}">
                        <td style="color: green">
                            ${requestScope.SUCCESS}
                        </td>
                    </c:when>
                </c:choose>
            </tr> 
        </c:forEach>            
    </tbody>
</table>



</body>
</html>
