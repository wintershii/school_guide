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
            vex[temp++] = p;
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
     * BFS寻找经过节点最少的路径
     * @param startId
     * @param arriveId
     * @return
     */
    public static PrintRoute BFSRoute(Integer startId, Integer arriveId) {
        setVisited();
        Deque<Integer> queue = new ArrayDeque<>();
        int pre[] = new int[vex.length];

        int init = getVexIndexByPlaceId(startId);
        int end = getVexIndexByPlaceId(arriveId);

        int distance = 0;

        for (int i = 0; i < pre.length; i++) {
            if (i == init) {
                pre[i] = -1;
            } else {
                pre[i] = init;
            }
        }

        queue.offer(init);
        visited[init] = true;
        while ( !queue.isEmpty()) {
            int placeIndex = queue.poll();
            //记录出队节点的前一个节点
            if (placeIndex == end) {
                break;
            }

            int[] ints = getNextPoints(placeIndex);

            for (int p : ints) {
                if (p != -1 && !visited[p]) {
                    queue.offer(p);
                    visited[p] = true;
                    pre[p] = placeIndex;
                }
            }

        }

        String[] routes = new String[vex.length];

        for (int i = 0; i < pre.length; i++) {
            System.out.print(i + ":" + pre[i] + " ");
        }
        System.out.println("end:" + end);

        int ans = 0;
        Deque<String> stack = new ArrayDeque<>();
        for (int i = end ; ;) {
            stack.push(vex[i].getName());
            distance++;
            i = pre[i];
            if (i == -1) {
                break;
            }
        }
        while (!stack.isEmpty()) {
            routes[ans++] = stack.pop();
        }

        return new PrintRoute(routes,distance - 2);
    }


    /**
     * 使用Dijkstra算法找两节点间的最短路径(带权)
     * @param startId
     * @param arriveId
     * @return
     */
    public static PrintRoute dijkstraRoute(Integer startId, Integer arriveId) {
        setVisited();

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

        return new PrintRoute(routes,dis[end]);
    }


    /**
     * Prime算法求出最佳布网
     * @param startId
     * @return
     */
    public static PrintRoute PrimeTree(Integer startId) {
        int pointNum = vex.length;
        int temp = 0;
        //辅助数组
        CloseEage[] close = new CloseEage[pointNum];
        for (int i = 0; i < vex.length; i++) {
            close[i] = new CloseEage();
        }

        for (int i = 0; i < vex.length; i++) {
            for (int j = 0; j < vex.length; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 99999;
                }
            }
        }

        String[] routes = new String[vex.length];
        int ans = 0;

        int start = getVexIndexByPlaceId(startId);
        routes[ans++] = vex[start].getName();

        for (int i = 0; i < pointNum; i++) {
            if (i != start) {
                close[i].adjVex = start;
                    close[i].lowCost = map[start][i];
            }
        }

        for (int j = 0; j < pointNum - 1; j++) {
            int min = Integer.MAX_VALUE;
            for (int k = 0; k < pointNum; k++) {
                if (close[k].lowCost != 0 && close[k].lowCost < min) {
                    temp = k;
                    min = close[k].lowCost;
                }
            }

            close[temp].lowCost = 0;

            routes[ans++] = vex[temp].getName();

            for (int k = 0; k < pointNum; k++) {
                if (k != temp && close[k].lowCost != 0 && map[temp][k] < close[k].lowCost ) {
                    close[k].lowCost = map[temp][k];
                    close[k].adjVex = temp;
                }
            }

        }

        return new PrintRoute(routes,0);
    }


    private static void setVisited() {
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
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
    public static int[] getNextPoints(int index) {
        int[] nextVexs = new int[vex.length];
        for (int i = 0; i < vex.length; i++) {
            nextVexs[i] = -1;
        }
        int temp = 0;
        for (int j = 0; j < map.length; j++) {
            if (map[index][j] != 0) {
                nextVexs[temp++] = j;
            }
        }
        return nextVexs;
    }
}

class CloseEage {
    int adjVex;
    int lowCost;
}