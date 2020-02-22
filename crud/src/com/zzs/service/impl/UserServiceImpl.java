package com.zzs.service.impl;

import com.zzs.dao.UserDao;
import com.zzs.dao.impl.UserDaoImpl;
import com.zzs.domain.PageInfo;
import com.zzs.domain.User;
import com.zzs.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return userDao.getAll();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User findUseById(String id) {
        return userDao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteById(Integer.parseInt(id));
    }

    @Override
    public void deleteSelect(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);

        }
    }

    @Override
    public PageInfo<User> getPageList(String _currentPage, String _row, Map<String, String[]> parameterMap) {
        int currentPage = Integer.parseInt(_currentPage);//这两个参数一定有值，在servlet中已经做处理了
        int row = Integer.parseInt(_row);//这两个参数一定有值，在servlet中已经做处理了

        PageInfo<User> pageInfo = new PageInfo<User>();
//        每页显示的记录数
        pageInfo.setRows(row);
//        正在显示的页码
        pageInfo.setCurrentPage(currentPage);
//        数据库中总的记录数？
        int toalCount = userDao.getToalCount(parameterMap);
        pageInfo.setTotalCount(toalCount);
//        分页后，总共分的页数
        int totalPage = (toalCount%row) ==0 ? (toalCount/row):(toalCount/row+1);
        pageInfo.setTotalPage(totalPage);
//每页数据从总数据的第几条数据开始
        int start = (currentPage -1)*row;
        List<User> users = userDao.getPageList(start,row,parameterMap);
        pageInfo.setList(users);


       return pageInfo;
    }


}
