<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shidongxuan
  Date: 19-1-2
  Time: 下午2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>地点信息</title>
</head>
<body>

<c:if test="${sessionScope.currentUser.role == 1}">
    <form action="<c:url value="/place/addPlace.do"/>" method="post">
        增加地点:
        地点名:<input type="text" name="placeName">

        简介:<input type="text" style="width: 185px;height: 40px" name="intro">

        <input type="submit" value="提交">
    </form>
</c:if>

<table style="text-align: center" border="1px">
    <tr>
        <td>地点名称</td>
        <td>地点简介</td>
        <c:if test="${sessionScope.currentUser.role == 1}">
            <td>操作</td>
        </c:if>
    </tr>

    <c:forEach items="${sessionScope.placeList}" var="list">
        <tr>
            <td>${list.name}</td>
            <td>${list.intro}</td>
            <c:if test="${sessionScope.currentUser.role == 1}">
                <td><a href="<c:url value="/place/deletePlace.do?placeId=${list.id}"/>">删除</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>


</body>
</html>
