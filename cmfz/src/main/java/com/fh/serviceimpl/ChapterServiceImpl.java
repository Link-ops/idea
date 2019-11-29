package com.fh.serviceimpl;

import com.fh.dao.ChapterDao;
import com.fh.entity.Chapter;
import com.fh.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Chapter> selectAllChapterByAlbumId(String id) {
        return chapterDao.selectAllChapterByAlbumId(id);
    }

    @Override
    public void deleteChapter(String[] ids) {
        chapterDao.deleteChapter(ids);
    }

    @Override
    public String insertChapter(Chapter chapter) {
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapter.setUploadTime(new Date());
        chapter.setTime("");
        chapter.setSize("");
        chapterDao.insertChapter(chapter);
        return s;
    }

    @Override
    public String updateChapter(Chapter chapter) {
        chapterDao.updateChapter(chapter);
        return chapter.getId();
    }
    @Override
    //修改一个章节的音乐资源路径
    public void updateChapterMusic(Chapter chapter){
        chapterDao.updateChapterMusic(chapter);
    }
}
