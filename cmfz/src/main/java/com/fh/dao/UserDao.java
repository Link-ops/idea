package com.fh.dao;



import com.fh.entity.AddressNumber;
import com.fh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    //查询用户所属地
    public List<AddressNumber> selectUserCountByProvince(String sex);
    //查询用户活跃情况
    public Integer selectUserCountByTime(String time);
    //添加一个用户
    public void addUser(User user);
    //用户登录
    public User loginUser(@Param("phone") String phone, @Param("password") String password);
    //根据id查询单个用户
    public User selectOneUser(String phone);
    //获取用户列表
    public List<User> selectAllUser(String[] id);
}
