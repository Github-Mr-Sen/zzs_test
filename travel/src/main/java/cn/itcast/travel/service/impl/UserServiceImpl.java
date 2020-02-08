package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoIml;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoIml();
    @Override
    public boolean findUserByname(User user) {

        return userDao.findUserBynameyname(user);
    }

    @Override
    public void saveUser(User user) {
        //-----------增加用户邮箱激活功能
        //设置数据库中的值
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.saveUser(user);
//        发送邮件
        String content="<a href='http://localhost:8080/travel/activeServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
    }

    @Override
    //
    public String getActiveCode(String code) {
        User user = userDao.getActiveCode(code);
        if (user == null) {
            return null;
        }
        userDao.updateStaus(code)
                ;

        return user.getCode();

    }

    @Override
    public User login(User user) {

        return userDao.login(user);
    }
}
