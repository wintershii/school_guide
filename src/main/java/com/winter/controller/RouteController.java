package com.winter.controller;

import com.winter.vo.MatrixMap;
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
        MatrixMap.initMap();
        String[] simpleRoute = MatrixMap.DFSWithoutWeight(startId,arriveId);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < simpleRoute.length && simpleRoute[i] != null; i++) {
            if (simpleRoute[i+1] == null) {
                sb.append(simpleRoute[i]);
            } else {
                sb.append(simpleRoute[i] + "--->");
            }
        }
        session.setAttribute("simpleRoute",sb.toString());
        return "redirect:/pages/main.jsp";
    }
}
