package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        ResultInfo info = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        String check = parameterMap.get("check")[0];
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (!check.equalsIgnoreCase(checkcode_server)) {
            info.setFlag(false);
            info.setErrorMsg("验证码不正确");
            String s = mapper.writeValueAsString(info);
            response.setContentType("applicate/json;charset=utf-8");
            response.getWriter().write(s);
            return;
        }
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service= new UserServiceImpl();
        User _user = service.login(user);
        if (_user == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        } else if(_user.getStatus().equalsIgnoreCase("n")){
            info.setFlag(false);
            info.setErrorMsg("请激活账号后在使用");
        }else {
            info.setFlag(true);
            request.getSession().setAttribute("usrinfo",_user);
        }


        String s = mapper.writeValueAsString(info);
        response.setContentType("applicate/json;charset=utf-8");
        response.getWriter().write(s);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
