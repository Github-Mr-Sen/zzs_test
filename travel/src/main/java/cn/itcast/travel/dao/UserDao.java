package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    boolean findUserBynameyname(User user);

    void saveUser(User user);

    User getActiveCode(String code);

    void updateStaus(String code);

    User login(User user);
}
