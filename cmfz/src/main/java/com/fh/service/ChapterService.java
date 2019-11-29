package com.fh.service;

import com.fh.entity.Chapter;

import java.util.List;

public interface ChapterService {
    //查询一个专辑下的所有章节
    public List<Chapter> selectAllChapterByAlbumId(String id);
    //删除专辑
    public void deleteChapter(String[] ids);
    //添加一个章节
    public String insertChapter(Chapter chapter);
    //修改一个章节
    public String updateChapter(Chapter chapter);
    //修改一个章节的音乐资源路径
    public void updateChapterMusic(Chapter chapter);
}
