<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shidongxuan
  Date: 19-1-2
  Time: 下午2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>路线信息</title>
</head>
<body>

<c:if test="${sessionScope.currentUser.role == 1}">
    <form action="<c:url value="/route/addRoute.do"/>" method="post">
        增加路线:
        起始地点:<select name="addStart">
        <option value="0">
            请选择
        </option>
        <c:forEach items="${sessionScope.placeList}" var="list">
            <option value="${list.id}=${list.name}">
                    ${list.name}
            </option>
        </c:forEach>
    </select>

        终止地点:<select name="addEnd">
        <option value="0">
            请选择
        </option>
        <c:forEach items="${sessionScope.placeList}" var="list">
            <option value="${list.id}=${list.name}">
                    ${list.name}
            </option>
        </c:forEach>
    </select>

        距离:<input type="text" name="distant">
        <input type="submit" value="提交">
    </form>
</c:if>

<table style="text-align: center" border="1px">
    <tr>
        <td>起始地点</td>
        <td>终止地点</td>
        <td>距离</td>
        <c:if test="${sessionScope.currentUser.role == 1}">
            <td>操作</td>
        </c:if>
    </tr>

    <c:forEach items="${sessionScope.routeList}" var="list">
        <tr>
            <td>${list.startName}</td>
            <td>${list.arriveName}</td>
            <td>${list.distant}</td>
            <c:if test="${sessionScope.currentUser.role == 1}">
                <td><a href="<c:url value="/route/deleteRoute.do?startId=${list.startId}&arriveId=${list.arriveId}"/>">删除</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>

</body>
</html>
