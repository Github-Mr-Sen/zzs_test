package com.zzs.service;

import com.zzs.domain.PageInfo;
import com.zzs.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();

    void addUser(User user);

    User findUseById(String id);

    void updateUser(User user);

    void deleteById(String id);

    void deleteSelect(String[] ids);

    PageInfo<User> getPageList(String currentPage, String row, Map<String, String[]> parameterMap);



//    public void addUser(User user);
}
