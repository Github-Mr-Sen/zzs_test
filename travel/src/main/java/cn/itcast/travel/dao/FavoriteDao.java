package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    int getTotalFbyRid(int rid);

    Favorite findByRidAndUid(int i, int uid);

    void add(int i, int uid);
}
