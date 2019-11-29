package com.fh.dao;

import com.fh.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    //查询一个专辑下的所有章节
    public List<Chapter> selectAllChapterByAlbumId(String id);
    //删除专辑
    public void deleteChapter(String[] ids);
    //添加一个章节
    public void insertChapter(Chapter chapter);
    //修改一个章节
    public void updateChapter(Chapter chapter);
    //修改一个章节的音乐资源路径
    public void updateChapterMusic(Chapter chapter);
}
