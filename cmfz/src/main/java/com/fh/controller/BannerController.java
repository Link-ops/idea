package com.fh.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.fh.entity.Banner;
import com.fh.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Scope(value = "prototype")
@RequestMapping("banner")
@ResponseBody
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("selectAll")
    public List<Banner> selectAllBanner(){
        return bannerService.selectAllBanner();
    }
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Banner banner,String oper,String[] id){
        /*
            add增
            edit改
            del删除
         */
        System.out.println(oper);
        if(oper.equals("add")){
            String s = UUID.randomUUID().toString();
            banner.setId(s);
            bannerService.insertBanner(banner);
            return s;
        }else if(oper.equals("edit")){
            bannerService.updateBanner(banner);
            return null;
        }else {
            bannerService.deleteSomeBanner(id);
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
        bannerService.updateBannerImg(bId,newFileName);
    }
    @RequestMapping("excelExport")
    public void excelExport(HttpServletResponse response){
        List<Banner> banners = bannerService.selectAllBanner();
        for (Banner banner : banners) {
            banner.setImg("D:\\source\\idea\\cmfz\\src\\main\\webapp\\img\\"+banner.getImg());
        }
        for (Banner banner : banners) {
            System.out.println(banner);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(
                new ExportParams("轮播图详情","图一"),Banner.class,banners
        );
        response.setHeader("content-Type","application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".xls");
        response.setCharacterEncoding("UTF-8");
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



