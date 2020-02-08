package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usrSessionInfoServlet")
public class UsrSessionInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usrinfo = (User)request.getSession().getAttribute("usrinfo");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(usrinfo);
        response.setContentType("applicate/json;charset=utf-8");
        response.getWriter().write(s);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
