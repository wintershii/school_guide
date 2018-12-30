<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shidongxuan
  Date: 18-12-29
  Time: 下午1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>西安邮电大学校园导航图</title>
    <style>
        .window {

        }
    </style>
</head>
<body>
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"/></font>
</c:if>
<c:if test="${!empty register}">
    <font color="red"><c:out value="${register}"/></font>
</c:if>
<div class="window">
    登录
    <form action="user/login.do" method="post">
        账号：<input type="text" required="required" name="username">
        <br/>
        密码：<input type="password" required="required" name="password">
        <br/>
        <input type="submit" value="登录">
        <a href="/pages/register.jsp">注册新用户</a>
    </form>
</div>

</body>
</html>
