package com.fh.serviceimpl;

import com.fh.dao.UserDao;
import com.fh.entity.AddressNumber;
import com.fh.entity.User;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, List<AddressNumber>> selectUserCountByProvince() {
        Map<String, List<AddressNumber>> map = new HashMap<>();
        List<AddressNumber> listN = userDao.selectUserCountByProvince("N");
        List<AddressNumber> listY = userDao.selectUserCountByProvince("Y");
        map.put("n",listN);
        map.put("y",listY);
        return map;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String,Integer> selectUserCountByTime() {
        Map<String,Integer> map = new HashMap<>();
        Integer n1 = userDao.selectUserCountByTime("7");
        Integer n2 = userDao.selectUserCountByTime("14");
        Integer n3 = userDao.selectUserCountByTime("21");
        map.put("n1",n1);
        map.put("n2",n2);
        map.put("n3",n3);
        return map;
    }

    @Override
    public Map<String,Object> addUser(User user) {
        Map<String,Object> map = new HashMap();
        if (userDao.selectOneUser(user.getPhone())!=null){
            map.put("error","-200");
            map.put("errmsg","该手机号已经存在");
        }else {
            String s = UUID.randomUUID().toString();
            user.setId(s);
            user.setCreateTime(new Date());
            userDao.addUser(user);
            map.put("uid", s);
            map.put("password", user.getPassword());
            map.put("phone", user.getPhone());
        }
        return map;
    }

    @Override
    public Map<String,Object> loginUser(String phone, String password) {
        Map<String,Object> map = new HashMap();
        User user = userDao.loginUser(phone, password);
        if(user==null){
            map.put("error","-200");
            map.put("errmsg","密码错误");
        }else {
            map.put("password",user.getPassword());
            map.put("farmington",user.getName());
            map.put("uid",user.getId());
            map.put("nickname",user.getNickName());
            map.put("gender",user.getSex());
            map.put("photo",user.getPhoto());
            map.put("location",user.getLocation());
            map.put("province",user.getProvince());
            map.put("city",user.getCity());
            map.put("description",user.getDescription());
            map.put("phone",user.getPhone());
        }
        return map;
    }

    @Override
    public Object selectAllUser(String[] id) {
        List<User> users = userDao.selectAllUser(id);
        if (users.isEmpty()){
            Map<String,String> map = new HashMap<>();
            map.put("error","-200");
            map.put("errmsg","会员列表为空");
            System.out.println("会员列表为空");
            return map;
        }else {
            System.out.println("会员列表不为空");
            for (User user : users) {
                System.out.println(user);
            }
            return users;
        }
    }
}
