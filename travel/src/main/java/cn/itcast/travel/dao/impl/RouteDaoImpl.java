package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import cn.itcast.travel.util.Utils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int getTotalcount(int cid, String rnmae) {
//        String sql = "select count(*) from tab_route where cid = ?";
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List param = new ArrayList();
        if(cid !=0){
            sb.append(" and cid = ? ");
            param.add(cid);
        }
        if(!Utils.strIsnull(rnmae)){
            sb.append(" and rname like ? ");
            param.add("%"+rnmae+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,param.toArray());
    }

    @Override
    public List<Route> queryPageByid(int cid, int start, int pageSize, String rnmae) {
//        String sql = "select * from tab_route where cid = ? limit ? , ?";
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List param = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid= ? ");
            param.add(cid);
        }
        if (!Utils.strIsnull(rnmae)) {
            sb.append(" and rname like ? ");
            param.add("%"+rnmae+"%");
        }
        sb.append(" limit ? ,? ");
        param.add(start);
        param.add(pageSize);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),param.toArray());
    }

    @Override
//    根据rid查询一条记录
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ? ";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
