package com.fh.service;

import com.fh.entity.Album;


import java.util.List;
import java.util.Map;

public interface AlbumService {
    //查询所有的专辑
    public List<Album> selectAllAlbum();
    //添加一个专辑
    public String insertAlbum(Album album);
    //删除专辑
    public void deleteAlbum(String[] ids);
    //修改一个专辑
    public String updateAlbum(Album album);
    //修改一个专辑的图片路径
    public void updateAlbumImg(String id, String img);
    //测试用查询所有
    public Map<String,Object> testSelectAll(String id,String uid);
}
