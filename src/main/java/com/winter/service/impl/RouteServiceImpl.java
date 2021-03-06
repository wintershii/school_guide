package com.winter.service.impl;

import com.winter.dao.RouteMapper;
import com.winter.pojo.Route;
import com.winter.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteMapper routeMapper;

    public List<Route> getAllRoutes() {
        return routeMapper.selectAllRoutes();
    }

    public int addNewRoute(Route route) {
        routeMapper.addNewRoute(route);
        return route.getId();
    }


    public int deleteRoute(Integer startId, Integer arriveId) {
        return routeMapper.deleteByStartIdAndArriveId(startId,arriveId);
    }
}
