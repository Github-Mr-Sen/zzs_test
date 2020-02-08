package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void queryPage(HttpServletRequest request, HttpServletResponse response){
        String _currentPage = request.getParameter("currentPage");
        int currentPage =1;
        String _pageSize = request.getParameter("pageSize");
        int pageSize =1;
        String _cid = request.getParameter("cid");
        int cid =0;
        String rnmae = request.getParameter("rname");
        try {
            rnmae = URLDecoder.decode(rnmae,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (_currentPage == null || _currentPage.trim().length() == 0) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(_currentPage);
        }
        if (_pageSize == null || _pageSize.trim().length() == 0) {
            pageSize = 5;
        } else {
            pageSize = Integer.parseInt(_pageSize);
        }
        if (_cid != null && _cid.trim().length() != 0) {
            cid = Integer.parseInt(_cid);
        }
       PageBean<Route>  pb = service.queryPageBycid(cid,currentPage,pageSize,rnmae);
super.writeValue(pb,response);
    }


//    详情页面数据
    public void  showDetail(HttpServletRequest request,HttpServletResponse response){
        String rid = request.getParameter("rid");
        Route route = service.detail(rid);
        super.writeValue(route,response);

    }

//    判断是否已经收藏过
    public void  isFavorite(HttpServletRequest request,HttpServletResponse response) {
        String rid = request.getParameter("rid");
        User usrinfo = (User) request.getSession().getAttribute("usrinfo");
        boolean falg = favoriteService.getFavoriteByuid(usrinfo.getUid(),rid);

        super.writeValue(falg,response);

    }

//    用户收藏线路
    public void  addFavorite(HttpServletRequest request,HttpServletResponse response) {
        String rid = request.getParameter("rid");
        User usrinfo = (User) request.getSession().getAttribute("usrinfo");
        ResultInfo info = new ResultInfo();
        int  uid =0 ;
        if (usrinfo != null) {
            uid = usrinfo.getUid();
        } else {

            info.setErrorMsg("请登录");
            info.setFlag(false);
            super.writeValue(info, response);
            return;
        }
        favoriteService.add(rid,uid);
        info.setFlag(true);
        super.writeValue(info,response);

    }



    }
