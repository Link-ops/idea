package com.fh;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.fh.dao.ChapterDao;
import com.fh.dao.UserDao;
import com.fh.entity.Admin;
import com.fh.entity.Album;
import com.fh.entity.Banner;
import com.fh.entity.Chapter;
import com.fh.service.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
        Admin admin = adminService.loginAdmin("fh", "630870");
        System.out.println(admin);
    }
    @Test
    public void testF1(){

        bannerService.deleteOneBanner("2");
        List<Banner> banners = bannerService.selectAllBanner();
        for (Banner banner1 : banners) {
            System.out.println(banner1);
        }
    }
    @Test
    public void testF2(){
        String[] ids={"b3858cc0-9cca-4547-acb9-0ea0b0784bcc","1"};
        albumService.deleteAlbum(ids);
    }
    @Test
    public void testF3() {
        List<Album> albums = albumService.selectAllAlbum();
        for (Album album : albums) {
            System.out.println(album);
        }
    }
    @Test
    public void testF4() throws IOException {
        List<Banner> banners = bannerService.selectAllBanner();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图详情","图一"),Banner.class,banners);
        workbook.write(new FileOutputStream(new File("D:/easypoi.xls")));
    }
    @Test
    public void testF5() {

    }
}
