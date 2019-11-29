package com.fh.serviceimpl;

import com.fh.dao.AlbumDao;
import com.fh.entity.Album;
import com.fh.entity.Chapter;
import com.fh.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Album> selectAllAlbum() {
        return albumDao.selectAllAlbum();
    }

    @Override
    public String insertAlbum(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);
        album.setUploadTime(new Date());
        album.setCreateTime(new Date());
        albumDao.insertAlbum(album);
        return s;
    }

    @Override
    public void deleteAlbum(String[] ids) {
        albumDao.deleteAlbum(ids);
    }

    @Override
    public String updateAlbum(Album album) {
        albumDao.updateAlbum(album);
        return album.getId();
    }

    @Override
    public void updateAlbumImg(String id, String img) {
        albumDao.updateAlbumImg(id,img);
    }

    @Override
    public Map<String, Object> testSelectAll(String id, String uid) {
        Map<String,Object> map = new HashMap<>();
        if(id==null||id.equals("")){
            map.put("code","500");
            map.put("msg","参数错误");
        }else {
            Album album = albumDao.selectOneAlbum(id);
            List<Chapter> chapters = albumDao.selectAllChapterOnAlbum(id);
            map.put("code","200");
            map.put("introduction",album);
            map.put("list",chapters);
        }
        return map;
    }
}
