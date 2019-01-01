<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shidongxuan
  Date: 18-12-28
  Time: 下午8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>西安邮电大学导航图</title>
    <style>
        .warrp{
            width:100%;
            height:100%;
            position: relative;
        }
        .imageShow{
            text-align: center;
        }
        .title{
            text-align: center;
        }
        .placeInfo{
            position: absolute;
            width:250px;
            left:50px;
        }
        .roteshow{
            position: absolute;
            width:250px;
            right:50px;
        }
    </style>
</head>
<body>
<div class="warrp">

    <div class="title">
        <h2>西安邮电大学导航图</h2>
    </div>

    <div class="placeInfo">
        <form action="/place/getIntro.do" method="post">
            <h5 style="text-align: center">地点信息查询</h5>

            <select name="placeId" id="placeId">
                <option value="0">
                    <c:choose>
                        <c:when test="${!empty choicePlaceName}">
                            ${choicePlaceName}
                        </c:when>
                        <c:otherwise>
                            请选择
                        </c:otherwise>
                    </c:choose>
                </option>
                <c:forEach items="${sessionScope.placeList}" var="list">
                    <option value="${list.id}">
                        ${list.name}
                    </option>
                </c:forEach>
            </select>
            <input id="submit" type="submit" value="查询">
            <br/>
            地点信息:<br/>
            <textarea style="width: 250px;height: 150px">${sessionScope.placeIntro}</textarea>
        </form>

    </div>



    <div class="roteshow">
        <h5 style="text-align: center">路线查询</h5>

        <form action="/route/getRouteBetweenTwoVex.do" method="post">
            起始地点:
            <select name="startId">
                <option value="0">
                    <c:choose>
                        <c:when test="${!empty startPlaceName}">
                            ${startPlaceName}
                        </c:when>
                        <c:otherwise>
                            请选择
                        </c:otherwise>
                    </c:choose>
                </option>
                <c:forEach items="${sessionScope.placeList}" var="list">
                    <option value="${list.id}">${list.name}</option>
                </c:forEach>
            </select>
            <br/>
            目标地点:
            <select name="arriveId">
                <option value="0">
                    <c:choose>
                        <c:when test="${!empty arrivePlaceName}">
                            ${arrivePlaceName}
                        </c:when>
                        <c:otherwise>
                            请选择
                        </c:otherwise>
                    </c:choose>
                </option>
                <c:forEach items="${sessionScope.placeList}" var="list">
                    <option value="${list.id}">${list.name}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="查询">
            <br/>
            游览路径:<br/>
            <textarea style="width: 250px;height: 150px">${sessionScope.simpleRoute}</textarea>
            访问路径:<br/>
            <textarea style="width: 250px;height: 150px">${sessionScope.weightRoute}</textarea>
        </form>



    </div>

    <div class="imageShow">
        <img src="/images/schoolMap.png">
    </div>
</div>





</body>
</html>
