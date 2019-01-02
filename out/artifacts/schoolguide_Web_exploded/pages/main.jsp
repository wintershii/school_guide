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
        .userShow{
            position: absolute;
            top: 0px;
            right: 0px;
            left: 0px;
            height: 30px;
            border-bottom: solid;
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
            left:170px;
            top: 50px;
            border: solid;
        }
        .circleShow{
            position: absolute;
            width: 250px;
            left: 170px;
            bottom: 50px;
            border: solid;
        }
        .roteshow{
            position: absolute;
            width:250px;
            right:170px;
            border: solid;
        }
        .manageShow{
            position: absolute;
            top: 30px;
            right: 0px;
            bottom: 0px;
            border-left: solid;
            border-bottom: solid;
            border-top: solid;
        }
    </style>
</head>
<body>
<div class="warrp">

    <div class="userShow">
        <a>欢迎使用西安邮电大学导航图!${sessionScope.currentUser.username}</a>
        <a href="<c:url value="/user/logout.do"/>">退出登录</a>
    </div>

    <div class="title">
        <h2>西安邮电大学导航图</h2>
    </div>

    <div class="placeInfo">
        <form action="<c:url value="/place/getIntro.do"/>" method="post">
            <h4 style="text-align: center">地点信息查询</h4>
            地点:
            <select name="placeId">
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
            <input type="submit" value="查询">
            <br/>
            地点信息:<br/>
            <textarea style="width: 250px;height: 150px">${sessionScope.placeIntro}</textarea>
        </form>

    </div>

    <div class="circleShow">
        <form action="<c:url value="/route/getBestCircle.do"/>" method="post">
            <h4 style="text-align: center">最佳布网查询</h4>
            布网出发点:
            <select name="circleStartId">
                <option value="0">
                    <c:choose>
                        <c:when test="${!empty circleChoice}">
                            ${circleChoice}
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
            <input type="submit" value="查询">
            <br/>
            布网顺序:<br/>
            <textarea style="width: 250px;height: 150px">${sessionScope.bestCircle}</textarea>
        </form>
    </div>

    <div class="roteshow">
        <h4 style="text-align: center">路线查询</h4>

        <form action="<c:url value="/route/getRouteBetweenTwoVex.do"/>" method="post">
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
            <a>访问最少地点路径:</a>
            <br/>
            <br/>
            <textarea style="width: 250px;height: 150px">${sessionScope.simpleRoute}</textarea>
            <br/>
            <a>行走距离最短路径:</a>
            <br/><br/>
            <textarea style="width: 250px;height: 150px">${sessionScope.weightRoute}</textarea>
        </form>



    </div>

    <div class="imageShow">
        <img src="<c:url value="/images/schoolMap.png"/>">
    </div>

    <c:if test="${sessionScope.currentUser.role == 1}">
        <div class="manageShow">
            <h4>管理员选项</h4>
            <br/>
            <a href="<c:url value="/pages/placeShow.jsp"/>">管理地点</a>
            <br/>
            <br/>
            <br/>
            <a href="<c:url value="/pages/routeShow.jsp"/>">管理路线</a>
        </div>
    </c:if>

</div>


</body>
</html>
