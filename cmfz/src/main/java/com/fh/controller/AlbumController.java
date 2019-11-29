package com.fh.controller;

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

@Controller
@Scope(value = "prototype")
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @ResponseBody
    @RequestMapping("selectAll")
    public List<Album> selectAllAlbum(){
        List<Album> albums = albumService.selectAllAlbum();
        return albums;
    }
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Album album,String[] id,String oper){
        System.out.println(oper);
        if(oper.equals("add")){
            String s = albumService.insertAlbum(album);
            return s;
        }else if (oper.equals("edit")){
            String s = albumService.updateAlbum(album);
            return s;
        }else {
            albumService.deleteAlbum(id);
            return null;
        }
    }
    @RequestMapping("upload")
    public void upload(MultipartFile img, String bId, HttpSession session){
        String realPath=session.getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName = img.getOriginalFilename();
        System.out.println(fileName);
        String newFileName=new Date().getTime()+"_"+fileName;
        try {
            img.transferTo(new File(file,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        albumService.updateAlbumImg(bId,newFileName);
    }
}
