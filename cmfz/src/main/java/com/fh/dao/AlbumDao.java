package com.fh.dao;

import com.fh.entity.Album;
import com.fh.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //查询所有的专辑
    public List<Album> selectAllAlbum();
    //添加一个专辑
    public void insertAlbum(Album album);
    //删除专辑
    public void deleteAlbum(String[] ids);
    //修改一个专辑
    public void updateAlbum(Album album);
    //修改一个专辑的图片路径
    public void updateAlbumImg(@Param("id") String id,@Param("img") String img);
    //查询一个专辑
    public Album selectOneAlbum(String id);
    //查询一个专辑下所有章节
    public List<Chapter> selectAllChapterOnAlbum(String id);
}
