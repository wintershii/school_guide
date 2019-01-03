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
        *{
            padding: 0;
        }
        .wrap{
            width:100%;
            height:100%;
            position: relative;
            background-image: url(<c:url value="/images/school_sence.png"/>);
            background-size: 100% 110%;
        }
        .window{
            position: absolute;
            text-align: center;
            top: 228px;
            left: 600px;
            width: 300px;
            height: 200px;
            border: solid;
            background-color: white;
            opacity: 0.85;
        }
    </style>
</head>
<body>
<div class="wrap">


    <h2 style="text-align: center;top: 152px; left: 603px;position: absolute">西安邮电大学地图导航系统</h2>
<div class="window">
    <br/>
    <a style="text-align: center; border: 2px">登录</a>
    <br/>
    <br/>
    <form action="<c:url value="user/login.do"/>" method="post">
        账号：<input type="text" required="required" name="username">
        <br/>
        密码：<input type="password" required="required" name="password">
        <br/>
        <br/>
        <input type="submit" value="登录">
        &nbsp;&nbsp;
        <a href="<c:url value="/pages/register.jsp"/>">注册</a>
        <br/>
        <c:if test="${!empty error}">
            <font color="red"><c:out value="${error}"/></font>
            <%
                session.removeAttribute("error");
            %>
        </c:if>
        <c:if test="${!empty register}">
            <font color="red"><c:out value="${register}"/></font>
            <%
                session.removeAttribute("register");
            %>
        </c:if>
    </form>
</div>

</div>
</body>
</html>
