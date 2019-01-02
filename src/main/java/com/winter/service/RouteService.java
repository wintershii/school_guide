package com.winter.service;

import com.winter.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteService {
    List<Route> getAllRoutes();
    int addNewRoute(Route route);
    int deleteRoute(Integer startId,Integer arriveId);
}
