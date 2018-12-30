package com.winter.controller;

import com.winter.pojo.Place;
import com.winter.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/getIntro",method = RequestMethod.POST)
    public String getPlaceIntro(int placeId, HttpSession session) {
        List<Place> list = (List<Place>) session.getAttribute("placeList");
        for (Place p : list) {
            if (p.getId() == placeId) {
                session.setAttribute("placeIntro",p.getName() + ":" + p.getIntro());
                return "redirect:/pages/main.jsp";
            }
        }
        return "redirect:/pages/main.jsp";
    }

    @RequestMapping(value = "/addPlace",method = RequestMethod.POST)
    public String addNewPlace(String placeName, String intro) {
        int count = placeService.addNewPlace(placeName,intro);
        if (count > 0) {
            return "redirect:/pages/main.jsp";
        }
        //添加失败
        return "redirect:/pages/main.jsp";
    }

    @RequestMapping(value = "/deletePlace",method = RequestMethod.POST)
    public String deletePlace(Integer placeId) {
        placeService.deletePlace(placeId);
        return "redirect:/pages/main.jsp";
    }

}
