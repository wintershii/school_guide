package com.winter.dao;

import com.winter.pojo.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKey(Place record);

    List<Place> selectAllPlaces();

    int insertNewPlace(@Param("placeName") String placeName,@Param("intro") String intro);
}