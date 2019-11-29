package com.fh.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ResponseBody
@Scope(value = "prototype")
@RequestMapping("kindeditor")
public class KindeditorController {
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile img, HttpServletRequest request)throws IOException{
        Map<String ,Object> map =new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName = img.getOriginalFilename();
        String newName = new Date().getTime()+"_"+fileName;
        img.transferTo(new File(realPath,newName));
        map.put("error",0);
        String scheme = request.getScheme();//传输协议
        InetAddress localHost = Inet4Address.getLocalHost();
        String address= localHost.getHostAddress();//请求地址
        int port = request.getServerPort();//获取端口号
        String path = request.getContextPath();//获取应用名
        String url=scheme+"://"+address+":"+port+path+"/img/"+newName;//拼接图片所在的资源路径
        System.out.println(url);
        map.put("url",url);
        return map;
    }
    @RequestMapping("getAllImg")
    public Map<String,Object> getAll(HttpServletRequest request) throws UnknownHostException {
        Map<String ,Object> map =new HashMap<>();
        List<Map<String ,Object>> list = new ArrayList<>();
        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        String[] names = file.list();
        for (String name : names) {
            Map<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("is_dir",false);
            stringObjectHashMap.put("has_file",false);
            File file1 = new File(realPath, name);
            long length = file1.length();
            stringObjectHashMap.put("filesize",length);
            stringObjectHashMap.put("is_photo",true);
            String s = name.substring(name.lastIndexOf(".") + 1);
            stringObjectHashMap.put("filetype",s);
            stringObjectHashMap.put("filename",name);
            boolean b = name.contains("_");
            if (b){
                String s1 = name.split("_")[0];
                Long aLong = Long.valueOf(s1);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s2 = format.format(aLong);
                stringObjectHashMap.put("datetime",s2);
            }
            if (!b){
                stringObjectHashMap.put("datetime",new Date());
            }

            stringObjectHashMap.put("datetime",name);
            stringObjectHashMap.put("dir_path","");
            list.add(stringObjectHashMap);
        }
        String scheme = request.getScheme();
        InetAddress localHost = Inet4Address.getLocalHost();
        String address = localHost.getHostAddress();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String url=scheme+"://"+address+":"+port+path+"/img/";
        map.put("moveup_dir_path","");
        map.put("current_dir_path","");
        map.put("current_url",url);
        map.put("total_count",names.length);
        map.put("file_list",list);
        return map;
    }
}
