package com.fh.dao;

import com.fh.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    public Admin loginAdmin(@Param("name") String name,@Param("password") String password);//根据账号密码查询一个管理员用于登录
}
