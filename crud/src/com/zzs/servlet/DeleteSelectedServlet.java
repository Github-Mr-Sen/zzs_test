package com.zzs.servlet;

import com.zzs.service.UserService;
import com.zzs.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] ids = request.getParameterValues("ids");
        UserService service = new UserServiceImpl();
        service.deleteSelect(ids);
        response.sendRedirect(request.getContextPath()+"/pageListServlet");

//        request.getRequestDispatcher("/pageListServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
