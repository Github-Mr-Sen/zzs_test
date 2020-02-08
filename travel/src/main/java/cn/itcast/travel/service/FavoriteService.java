package cn.itcast.travel.service;

public interface FavoriteService {
    boolean getFavoriteByuid(int uid, String rid);

    void add(String rid, int uid);
}
