package com.winter.controller;

import com.winter.pojo.Route;
import com.winter.service.RouteService;
import com.winter.vo.MatrixMap;
import com.winter.vo.PrintRoute;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "/getRouteBetweenTwoVex.do",method = RequestMethod.POST)
    public String getRouteBetweenTwoVex(Integer startId, Integer arriveId, HttpSession session,HttpServletRequest request) {
        String head = request.getContextPath();
        if (startId == 0 || arriveId == 0) {
            return "redirect:/pages/main.jsp";
        }
        MatrixMap.initMap();

        PrintRoute simpleRoute = MatrixMap.BFSRoute(startId,arriveId);

        PrintRoute weightRoute = MatrixMap.dijkstraRoute(startId,arriveId);

        session.setAttribute("startPlaceName",MatrixMap.getNameByPlaceId(startId));
        session.setAttribute("arrivePlaceName",MatrixMap.getNameByPlaceId(arriveId));

        StringBuilder sb = new StringBuilder();
        String[] simple = simpleRoute.getRoutes();
        for (int i = 0; i < simple.length && simple[i] != null; i++) {
            if (simple[i+1] == null) {
                sb.append(simple[i]);
                sb.append("  总共中转" + simpleRoute.getDistance() + "个节点");
            } else {
                sb.append(simple[i] + "--->");
            }
        }
        session.setAttribute("simpleRoute",sb.toString());

        StringBuilder sb2 = new StringBuilder();
        String[] weight = weightRoute.getRoutes();
        for (int i = 0; i < weight.length && weight[i] != null; i++) {
            if (weight[i+1] == null) {
                sb2.append(weight[i]);
                sb2.append("  路线总长为:" + weightRoute.getDistance() + "米");
            } else {
                sb2.append(weight[i] + "--->");
            }
        }
        session.setAttribute("weightRoute",sb2.toString());

        return "redirect:/pages/main.jsp";
    }


    @RequestMapping(value = "/getBestCircle.do", method = RequestMethod.POST)
    public String getBestCircle(Integer circleStartId,HttpSession session,HttpServletRequest request) {
        String head = request.getContextPath();
        if (circleStartId == 0) {
            return "redirect:/pages/main.jsp";
        }
        MatrixMap.initMap();

        PrintRoute circle = MatrixMap.PrimeTree(circleStartId);
        StringBuilder sb = new StringBuilder();
        String[] best = circle.getRoutes();
        for (int i = 0; i < best.length; i++) {
            if (i == best.length - 1) {
                sb.append(best[i]);
            } else {
                sb.append(best[i] + "--->");
            }
        }
        session.setAttribute("bestCircle",sb.toString());
        session.setAttribute("circleChoice",MatrixMap.getNameByPlaceId(circleStartId));
        return "redirect:/pages/main.jsp";
    }

    @RequestMapping(value = "/addRoute.do",method = RequestMethod.POST)
    public String addRoute(String addStart,String addEnd, Integer distant, HttpSession session,HttpServletRequest request) {
        String head = request.getContextPath();
        String[] start = addStart.split("=");
        String[] end = addEnd.split("=");
        Integer startId = Integer.parseInt(start[0]);
        String startName = start[1];
        Integer arriveId = Integer.parseInt(end[0]);
        String arriveName = end[1];
        Route route = new Route(startId,startName,arriveId,arriveName,distant);
        int id = routeService.addNewRoute(route);
        route.setId(id);

        List<Route> list = (List<Route>) session.getAttribute("routeList");
        list.add(route);
        session.setAttribute("routeList",list);
        return "redirect:/pages/routeShow.jsp";
    }

    @RequestMapping(value = "/deleteRoute.do")
    public String deleteRoute(Integer startId, Integer arriveId, HttpSession session, HttpServletRequest request) {
        String head = request.getContextPath();
        routeService.deleteRoute(startId,arriveId);

        List<Route> list = (List<Route>) session.getAttribute("routeList");
        int index = 0;
        for (Route r : list) {
            if (r.getStartId() == startId && r.getArriveId() == arriveId) {
                break;
            }
            index++;
        }
        list.remove(index);
        session.setAttribute("routeList",list);
        return "redirect:/pages/routeShow.jsp";
    }
}
