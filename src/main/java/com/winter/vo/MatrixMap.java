package com.winter.vo;

import com.winter.pojo.Place;
import com.winter.pojo.Route;
import com.winter.service.PlaceService;
import com.winter.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.util.*;

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
            map[n][m] = route[i].getDistant();
        }
    }


    /**
     * 使用Dijkstra算法找两节点间的最短路径(带权)
     * @param startId
     * @param arriveId
     * @return
     */
    public static PrintRoute dijkstraRoute(Integer startId, Integer arriveId) {
        for (int i = 0; i < vex.length; i++){
            for (int j = 0; j < vex.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        int[] dis = new int[vex.length];
        int startIndex = getVexIndexByPlaceId(startId);
        boolean[] visited = new boolean[vex.length];

        int[] pre = new int[vex.length];


        for (int i = 0; i < pre.length; i++) {
            if (i == startIndex) {
                pre[i] = -1;
            } else {
                pre[i] = startIndex;
            }
        }

        for (int i = 0; i < vex.length; i++) {
            if (map[startIndex][i] != 0) {
                dis[i] = map[startIndex][i];
            } else {
                dis[i] = 9999;
            }
        }

        visited[startIndex] = true;
        pre[startIndex] = -1;

        for (int i = 0; i < vex.length - 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < vex.length; j++) {
                if ( !visited[j] && dis[j] < min) {
                    min = dis[j];
                    startIndex = j;
                }
            }

            visited[startIndex] = true;

            for (int j = 0; j < vex.length; j++) {
                int tmp = Math.min(dis[j],dis[startIndex] + map[startIndex][j]);
                if (!visited[j] && map[startIndex][j] != 0) {
                    if (tmp < dis[j]) {
                        dis[j] = tmp;
                        pre[j] = startIndex;
                    }
                }
            }
        }

        String[] routes = new String[vex.length];
        int end = getVexIndexByPlaceId(arriveId);

        for (int i = 0; i < pre.length; i++) {
            System.out.print(i + ":" + pre[i] + " ");
        }
        System.out.println("end:" + end);

        int ans = 0;
        Deque<String> stack = new ArrayDeque<>();
        for (int i = end ; ;) {
            stack.push(vex[i].getName());
            i = pre[i];
            if (i == -1) {
                break;
            }
        }
        while (!stack.isEmpty()) {
            routes[ans++] = stack.pop();
        }

        System.out.println(routes);
        return new PrintRoute(routes,dis[end]);
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
