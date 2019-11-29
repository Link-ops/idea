package com.fh.controller;

import com.fh.entity.Chapter;
import com.fh.service.ChapterService;
import com.fh.util.FileSize;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@Scope(value = "prototype")
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @ResponseBody
    @RequestMapping("selectAll")
    public List<Chapter> selectAllChapter(String albumId) {
        List<Chapter> chapters = chapterService.selectAllChapterByAlbumId(albumId);
        return chapters;
    }

    @ResponseBody
    @RequestMapping("edit")
    public String edit(Chapter chapter, String[] id, String oper, String albumId) {
        System.out.println(oper);
        if (oper.equals("add")) {
            chapter.setAlbumId(albumId);
            String s = chapterService.insertChapter(chapter);
            return s;
        } else if (oper.equals("edit")) {
            String s = chapterService.updateChapter(chapter);
            return s;
        } else {
            chapterService.deleteChapter(id);
            return null;
        }
    }

    @RequestMapping("upload")
    public void upload(MultipartFile music, String bId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/music/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = music.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + fileName;
        File f = new File(file, newFileName);
        try {
            music.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MP3AudioHeader audioHeader = null;
        try {
            MP3File read = (MP3File) AudioFileIO.read(f);
            audioHeader = (MP3AudioHeader) read.getAudioHeader();
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        String printSize = FileSize.getPrintSize(f.length());
        Chapter chapter = new Chapter();
        chapter.setId(bId);
        chapter.setMusic(newFileName);
        chapter.setSize(printSize);
        double trackLength = (double) audioHeader.getTrackLength();
        double time = trackLength / 60;
        String s = String.valueOf(time);
        s = s.replace(".", "分钟");
        s = s + "秒";
        chapter.setTime(s);
        chapterService.updateChapterMusic(chapter);
    }
}