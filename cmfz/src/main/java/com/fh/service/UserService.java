package com.fh.service;

import com.fh.entity.AddressNumber;
import com.fh.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //查询用户所属地
    public Map<String, List<AddressNumber>> selectUserCountByProvince();
    //查询用户活跃情况
    public Map<String,Integer> selectUserCountByTime();
    //添加一个用户
    public Map<String,Object> addUser(User user);
    //用户登录
    public Map<String,Object> loginUser(String phone,String password);
    //查询用户列表
    public Object selectAllUser(String[] id);
}
