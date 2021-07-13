<%-- 
    Document   : dayspromotion
    Created on : Jun 6, 2021, 11:40:08 AM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER eq null}">
    <c:redirect url="logout"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PromotionDays Page</title>
    </head>
    <body>
        <h3>Welcome,${sessionScope.USER.username}
            <a href="logout">logout</a>
        </h3>
        <p>
            <a href="loaddata">Again</a>
        </p>
        <p style="color: red">
            ${requestScope.DATENULL}
        </p>
        <p>           
        <form action="viewdayspromotion">
            Date <input type="date" name="assignmentdate" value="" />
            <input type="submit" value="SearchDate" name="btAction"/>
        </form>
    </p>            
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>PromotionType</th>
                <th>Date</th>
                <th>Rank</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="list" items="${sessionScope.LIST_PROMOTIONBYDATE}" varStatus="counter">
                <tr>                    
                    <td>${counter.count}</td>
                    <td>${list.getTypePromotionsDTO().getTypename()}</td>
                    <td>${list.rank}</td>
                    <td>${list.assignmentdate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
