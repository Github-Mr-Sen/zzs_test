package com.zzs.servlet;

import com.zzs.domain.PageInfo;
import com.zzs.domain.User;
import com.zzs.service.UserService;
import com.zzs.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/pageListServlet")
public class PageListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage") == null?"1":request.getParameter("currentPage");
        String row = request.getParameter("row") == null?"5":request.getParameter("row");
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserService service = new UserServiceImpl();
//        List<User> users = service.getPageList(currentPage,row);
        PageInfo<User> users = service.getPageList(currentPage,row,parameterMap);
        System.out.println(users);
        request.setAttribute("users",users);
        request.setAttribute("condition",parameterMap);
//        response.sendRedirect(request.getContextPath() +"/list.jsp");

        request.getRequestDispatcher("/list.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
