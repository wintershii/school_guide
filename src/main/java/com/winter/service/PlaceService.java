package com.winter.service;

import com.winter.pojo.Place;

import java.util.List;

public interface PlaceService {
    List<Place> getAllPlaces();
    int addNewPlace(String placeName, String intro);
    void deletePlace(Integer placeId);
}
