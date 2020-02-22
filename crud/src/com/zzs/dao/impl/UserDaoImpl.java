package com.zzs.dao.impl;

import com.zzs.dao.UserDao;
import com.zzs.domain.User;
import com.zzs.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> getAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());


    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);

    }

    @Override
    public void updateUser(User user) {
        System.out.println(user);
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";

        int update = template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        System.out.println(update);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from user where id =?";
        template.update(sql,id);
    }

    @Override
    public int getToalCount(Map<String, String[]> parameterMap) {
        String sql = "select count(*) from user where 1=1";
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer(sql);
        if (!parameterMap.isEmpty()){
            Set<String> keySet = parameterMap.keySet();
            for (String s : keySet) {
                if ("currentPage".equals(s) || "row".equals(s)){
                    continue;
                }
                String value = parameterMap.get(s)[0];
                if (value !=null && !"".equals(value.trim())){

                    sb.append(" and "+ s + " like ? ");
                    params.add("%"+value+"%");
                }

            }
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());

    }

    @Override
    public List<User> getPageList(int start, int row, Map<String, String[]> parameterMap) {
//        String sql = "select * from user limit ?,?";
        String sql = "select * from user where 1=1 ";
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer(sql);
        if (!parameterMap.isEmpty()){
            Set<String> keySet = parameterMap.keySet();
            for (String s : keySet) {
                if ("currentPage".equals(s) || "row".equals(s)){
                    continue;
                }
                String value = parameterMap.get(s)[0];
                if (value !=null && !"".equals(value.trim())){

                    sb.append(" and "+ s + " like ? ");
                    params.add("%"+value+"%");
                }

            }
        }
        sb.append(" limit ?,?");
        params.add(start);
        params.add(row);
        sql = sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
