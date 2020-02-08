package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoIml implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public boolean findUserBynameyname(User user) {
        User u = null;
        String sql = "select * from tab_user where username = ?";
        try {

            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return u==null?false:true;
    }

    @Override
    public void saveUser(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql

        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public User getActiveCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);

        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public void updateStaus(String code) {
        String sql = "update tab_user set status = 'Y' where code = ?";
        template.update(sql, code);
    }

    @Override
//    用户名和密码查询用户
    public User login(User user) {
        User _user = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            //2.执行sql
            _user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(),user.getPassword());
        } catch (Exception e) {
e.getStackTrace();
        }
        return _user;
    }


    @Test
    public void test1(){
        User user = new User();
        user.setName("123");
        String sql = "select * from tab_user where username = ?";
        User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),user.getName());

    }
}
