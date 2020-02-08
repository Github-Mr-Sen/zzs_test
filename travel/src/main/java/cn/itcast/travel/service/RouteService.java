package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    PageBean<Route> queryPageBycid(int cid, int currentPage, int pageSize, String rnmae);

    Route detail(String rid);
}
