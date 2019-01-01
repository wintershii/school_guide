package com.winter.vo;

public class PrintRoute {
    private String[] routes;
    private Integer distance;

    public PrintRoute(String[] routes, Integer distance) {
        this.routes = routes;
        this.distance = distance;
    }

    public String[] getRoutes() {
        return routes;
    }

    public void setRoutes(String[] routes) {
        this.routes = routes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
