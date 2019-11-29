package com.fh.test;

import com.fh.entity.Album;
import com.fh.service.AlbumService;
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
import java.util.Map;

@Controller
@ResponseBody
@Scope(value = "prototype")
@RequestMapping("testalbum")
public class TestAlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("wen")
    public Map<String,Object> selectAll(String id,String uid){
        return albumService.testSelectAll(id,uid);
    }
}
