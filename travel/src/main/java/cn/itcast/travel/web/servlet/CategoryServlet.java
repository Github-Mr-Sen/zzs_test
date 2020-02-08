package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategroryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    CategoryService service = new CategroryServiceImpl();
    public void findAll (HttpServletRequest request, HttpServletResponse response){
        List<Category> all = service.findAll();
        super.writeValue(all,response);
    }



}
