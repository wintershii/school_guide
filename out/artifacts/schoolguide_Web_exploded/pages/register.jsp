<%--
  Created by IntelliJ IDEA.
  User: shidongxuan
  Date: 18-12-29
  Time: 下午3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <style>
        .registerWindow {

        }
    </style>
</head>
<body>
<c:if test="${!empty exist}">
    <font color="red"><c:out value="${exist}"/></font>
</c:if>
<div class="registerWindow">
    注册
    <form action="/user/register.do" method="post">
        账号：<input type="text" required="required" name="username">
        <br/>
        密码：<input type="password" required="required" name="password">
        <br/>
        <input type="submit" value="注册">
    </form>
</div>
</body>
</html>
