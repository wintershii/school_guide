package com.winter.vo;

import com.winter.pojo.Place;
import com.winter.pojo.Route;
import com.winter.service.PlaceService;
import com.winter.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.toArray;

/**
 * value object 类
 * 使用邻接矩阵的方式封装地图中的节点与路线信息
 */

public class MatrixMap {
    private static Place[] vex;
    private static Route[] route;
    private static int[][] map;
    private static boolean[] visited;

    private static PlaceService placeService;


    private static RouteService routeService;

    public void setPlaceService(PlaceService placeService) {
        MatrixMap.placeService = placeService;
    }

    public void setRouteService(RouteService routeService) {
        MatrixMap.routeService = routeService;
    }


    /**
     * 初始化
     */
    public static void initMap() {
        List<Place> list = placeService.getAllPlaces();
        vex = new Place[list.size()];
        int temp = 0;
        for (Place p : list) {
            vex[temp] = list.get(temp);
            temp++;
        }

        List<Route> routes = routeService.getAllRoutes();
        route = new Route[routes.size()];
        temp = 0;
        for (Route r : routes) {
            route[temp] = routes.get(temp);
            temp++;
        }
        map = new int[vex.length][vex.length];
        visited = new boolean[vex.length];
        for (int i = 0; i < route.length; i++) {
            int m = getVexIndexByPlaceId(route[i].getStartId());
            int n = getVexIndexByPlaceId(route[i].getArriveId());
            map[m][n] = route[i].getDistant();
        }
    }



    public String[] dijkstraRoute(Integer startId, Integer arriveId) {

    }




    /**
     * 根据地点名获取对应数组下标
     * @return
     */
    public static int getVexIndexByPlaceId(int placeId) {
        for (int i = 0; i < vex.length; i++) {
            if (placeId == vex[i].getId()) {
                return i;
            }
        }
        return -1;
    }


    public static String getNameByPlaceId(int placeId) {
        for (int i = 0; i < vex.length; i++) {
            if (placeId == vex[i].getId()) {
                return vex[i].getName();
            }
        }
        return "";
    }


    /**
     * 返回指定点的邻接点
     * @return
     */
    public static int[] getNextPoints(int id) {
        int[] nextVexs = new int[vex.length];
        for (int i = 0; i < vex.length; i++) {
            nextVexs[i] = -1;
        }
        int t = 0;
        int temp = 0;
        for (int i = 0; i < vex.length; i++) {
            if (id == vex[i].getId()) {
                t = i;
            }
        }

        for (int j = 0; j < map.length; j++) {
            if (map[t][j] != 0) {
                nextVexs[temp++] = vex[j].getId();
            }
        }
        return nextVexs;
    }
}
