package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    int getTotalcount(int cid, String rnmae);

    List<Route> queryPageByid(int cid, int start, int pageSize, String rnmae);

    Route findOne(int i);
}
