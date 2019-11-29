package com.fh.controller;

import com.fh.entity.Admin;
import com.fh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Scope(value = "prototype")
@RequestMapping("/admin")
@ResponseBody
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public Object loginAdmin(String name, String password, HttpSession session, String enCode){
        Admin admin = adminService.loginAdmin(name, password);
        System.out.println("name = " + name);
        System.out.println("password = " + password);
        System.out.println("enCode = " + enCode);
        System.out.println(session.getAttribute("KAPTCHA_SESSION_KEY"));
        if (session.getAttribute("KAPTCHA_SESSION_KEY").equals(enCode)){
            if (adminService.loginAdmin(name,password)!=null) {
                session.setAttribute("admin",adminService.loginAdmin(name,password));
                return "success";
            }else {
                return "fail";
            }
        }else {
            System.out.println("验证码错误");
            return "codeFail";
        }
    }
}
