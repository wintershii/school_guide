package com.winter.service.impl;

import com.winter.dao.PlaceMapper;
import com.winter.pojo.Place;
import com.winter.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    public List<Place> getAllPlaces() {
        return placeMapper.selectAllPlaces();
    }

    public int addNewPlace(String placeName, String intro) {
        Place place = new Place();
        place.setName(placeName);
        place.setIntro(intro);
        placeMapper.insertNewPlace(place);
        return place.getId();
    }

    public void deletePlace(Integer placeId) {
        placeMapper.deleteByPrimaryKey(placeId);
    }
}
