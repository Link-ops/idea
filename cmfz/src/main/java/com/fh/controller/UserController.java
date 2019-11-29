package com.fh.controller;

import com.fh.entity.AddressNumber;
import com.fh.entity.User;
import com.fh.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@Scope(value = "prototype")
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("time")
    public Map<String,Integer> selectUserCountByTime(){
        return userService.selectUserCountByTime();
    }
    @RequestMapping("map")
    public Map<String, List<AddressNumber>> selectUserCountByProvince(){
        return userService.selectUserCountByProvince();
    }
    @RequestMapping("regist")
    public Map<String,Object> addUser(User user){
        GoEasy goEasy =null;
        Map<String,Object> s = null;
        try{
            s = userService.addUser(user);
            goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-c3f6eb39b6784839a30c7bb2a7001a37");
            goEasy.publish("MyChannel","注册成功!");
        }catch (Exception e){
            e.printStackTrace();
            goEasy.publish("MyChannel","注册失败!");
        }
        return s;
    }
    @RequestMapping("login")
    public Map<String,Object> userLogin(String phone,String password){
        return userService.loginUser(phone,password);
    }
    @RequestMapping("member")
    public Object selectAllUser(String[] uid){
        return userService.selectAllUser(uid);
    }
}