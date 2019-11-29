package com.fh.service;

import com.fh.entity.Admin;
public interface AdminService {
    public Admin loginAdmin(String name,String password);//根据账号密码查询一个管理员用于登录
}
