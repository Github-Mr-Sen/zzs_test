package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    RouteDao routeDao = new RouteDaoImpl();

    SellerDao sellerDao = new SellerDaoImpl();

    RouteImgDao routeImgDao = new RouteImgDaoImpl();

    FavoriteDao favoriteDao = new FavoriteDaoImpl();
    public PageBean<Route> queryPageBycid(int cid, int currentPage, int pageSize, String rnmae) {
        PageBean pb = new PageBean();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int count = routeDao.getTotalcount(cid,rnmae);
        pb.setTotalCount(count);
        int totalp = count%pageSize ==0?count/pageSize:(count/pageSize)+1;
        pb.setTotalPage(totalp);
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> routes = routeDao.queryPageByid(cid,start,pageSize,rnmae);
        pb.setList(routes);
        return pb;
    }

    @Override
    public Route detail(String rid) {
        Route route = routeDao.findOne(Integer.parseInt(rid));

        Seller seller = sellerDao.getSeller(route.getSid());
        List<RouteImg> imgs = routeImgDao.getImg(route.getRid());
        route.setSeller(seller);
        route.setRouteImgList(imgs);
        int count = favoriteDao.getTotalFbyRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
