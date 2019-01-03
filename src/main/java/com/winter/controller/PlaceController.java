package com.winter.controller;

import com.winter.pojo.Place;
import com.winter.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    /**
     * 获取指定地点信息
     * @param placeId
     * @param session
     * @return
     */
    @RequestMapping(value = "/getIntro.do",method = RequestMethod.POST)
    public String getPlaceIntro(int placeId, HttpSession session) {
        if (placeId == 0) {
            return "redirect:/pages/main.jsp";
        }
        List<Place> list = (List<Place>) session.getAttribute("placeList");
        for (Place p : list) {
            if (p.getId() == placeId) {
                session.setAttribute("placeIntro",p.getName() + ":" + p.getIntro());
                session.setAttribute("choicePlaceId",placeId);
                session.setAttribute("choicePlaceName",p.getName());
                return "redirect:/pages/main.jsp";
            }
        }
        return "redirect:/pages/main.jsp";
    }

    /**
     * 增加地点
     * @param placeName
     * @param intro
     * @param session
     * @return
     */
    @RequestMapping(value = "/addPlace.do",method = RequestMethod.POST)
    public String addNewPlace(String placeName, String intro,HttpSession session) {
        int id = placeService.addNewPlace(placeName,intro);
        List<Place> placeList = (List<Place>) session.getAttribute("placeList");
        Place p = new Place();
        p.setId(id);
        p.setName(placeName);
        p.setIntro(intro);
        placeList.add(p);
        session.setAttribute("placeList",placeList);
        return "redirect:/pages/placeShow.jsp";
    }

    /**
     * 删除地点
     * @param placeId
     * @param session
     * @return
     */
    @RequestMapping(value = "/deletePlace.do",method = RequestMethod.GET)
    public String deletePlace(Integer placeId,HttpSession session) {
        placeService.deletePlace(placeId);
        List<Place> list = (List<Place>) session.getAttribute("placeList");
        int index = 0;
        for (Place p : list) {
            if (p.getId() == placeId) {
                break;
            }
            index++;
        }
        list.remove(index);
        session.setAttribute("placeList",list);
        return "redirect:/pages/placeShow.jsp";
    }

}
