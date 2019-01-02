package com.winter.dao;

import com.winter.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);

    List<Route> selectAllRoutes();

    int addNewRoute(Route route);

    int deleteByStartIdAndArriveId(@Param("startId") Integer startId, @Param("arriveId") Integer arriveId);
}