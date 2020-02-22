package com.zzs.dao;

import com.zzs.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> getAll();

    void addUser(User user);

    User findUserById(int i);

    void updateUser(User user);

    void deleteById(int i);

    int getToalCount(Map<String, String[]> parameterMap);

    List<User> getPageList(int start, int row, Map<String, String[]> parameterMap);
}
