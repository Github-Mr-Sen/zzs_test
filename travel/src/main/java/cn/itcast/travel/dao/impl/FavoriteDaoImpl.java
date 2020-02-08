package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
//    统计某条线路的收藏次数
    public int getTotalFbyRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ? ";

        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public Favorite findByRidAndUid(int i, int uid) {
        Favorite favorite = null;
        String sql = null;
        try {
            sql = "select * from tab_favorite where rid = ? and uid = ? ";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), i, uid);
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return favorite;


    }

    @Override
    public void add(int i, int uid) {
        String sql = null;
        try {
            sql = "insert into tab_favorite values (?, ?,?)";
            int update = template.update(sql, i, new Date(), uid);
            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
