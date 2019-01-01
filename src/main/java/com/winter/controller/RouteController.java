package com.winter.controller;

import com.winter.vo.MatrixMap;
import com.winter.vo.PrintRoute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    @RequestMapping(value = "/getRouteBetweenTwoVex.do",method = RequestMethod.POST)
    public String getRouteBetweenTwoVex(Integer startId, Integer arriveId, HttpSession session) {
        if (startId == 0 || arriveId == 0) {
            return "redirect:/pages/main.jsp";
        }
        MatrixMap.initMap();
//        todo
        PrintRoute simpleRoute = MatrixMap.dijkstraRoute(startId,arriveId);

        PrintRoute weightRoute = MatrixMap.dijkstraRoute(startId,arriveId);

        session.setAttribute("startPlaceName",MatrixMap.getNameByPlaceId(startId));
        session.setAttribute("arrivePlaceName",MatrixMap.getNameByPlaceId(arriveId));

        StringBuilder sb = new StringBuilder();
        String[] simple = simpleRoute.getRoutes();
        for (int i = 0; i < simple.length && simple[i] != null; i++) {
            if (simple[i+1] == null) {
                sb.append(simple[i]);
                sb.append("  总共经过" + simpleRoute.getDistance() + "个节点");
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
}
