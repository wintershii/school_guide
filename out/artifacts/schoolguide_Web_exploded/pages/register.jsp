<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        .regWrap{
            width:100%;
            height:100%;
            position: relative;
            background-image: url("/images/school_sence.png");
            background-size: 100% 110%;
        }
        .registerWindow {
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
<div class="regWrap">

<div class="registerWindow">
    <br/>
    <a style="text-align: center; border: 2px">注册</a>
    <br/>
    <form action="<c:url value="/user/register.do"/>" method="post">
        账号：<input type="text" required="required" name="username">
        <br/>
        密码：<input type="password" required="required" name="password">
        <br/>
        <br/>
        <input type="submit" value="注册">
        <br/>
        <c:if test="${!empty exist}">
            <font color="red"><c:out value="${exist}"/></font>
            <%
                session.removeAttribute("exist");
            %>
        </c:if>
    </form>
</div>

</div>
</body>
</html>
