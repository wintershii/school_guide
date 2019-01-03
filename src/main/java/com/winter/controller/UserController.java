package com.winter.controller;

import com.winter.common.Const;
import com.winter.pojo.Place;
import com.winter.pojo.Route;
import com.winter.pojo.User;
import com.winter.service.PlaceService;
import com.winter.service.RouteService;
import com.winter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private RouteService routeService;
    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        boolean isValid = userService.checkAccount(username,password);
        if (!isValid) {
            session.setAttribute("error","用户名或密码错误");
            return "redirect:/index.jsp";
        }
        User user = userService.getUser(username);
        user.setPassword("");
        session.setAttribute(Const.CURRENT_USER,user);
        List<Place> placeList = placeService.getAllPlaces();
        List<Route> routeList = routeService.getAllRoutes();

        session.setAttribute("placeList",placeList);
        session.setAttribute("routeList",routeList);

        return "redirect:/pages/main.jsp";
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public String register(String username, String password,HttpSession session) {
        boolean hasRegistered = userService.checkUsername(username);
        if (hasRegistered) {
            session.setAttribute("exist","用户名已存在!");
            return "redirect:/pages/register.jsp";
        }
        boolean registerSuccess = userService.register(username,password);
        if (registerSuccess) {
            session.setAttribute("register","注册成功，请登录!");
            return "redirect:/index.jsp";
        }
        session.setAttribute("register","注册失败!");
        return "redirect:/index.jsp";
    }

    /**
     * 用户退出
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    public String logout(HttpSession session, HttpServletRequest request) {
        String head = request.getContextPath();
        session.invalidate();
        return "redirect:/index.jsp";
    }
}
