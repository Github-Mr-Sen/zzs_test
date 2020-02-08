package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    boolean findUserByname(User user);

    void saveUser(User user);

    String getActiveCode(String code);

    User login(User user);
}
