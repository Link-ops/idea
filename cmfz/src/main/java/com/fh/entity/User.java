package com.fh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private String id;
    private String name;
    private String province;
    private Date createTime;
    private String sex;
    private String nickName;
    private String photo;
    private String location;
    private String city;
    private String description;
    private String phone;
    private String password;
}
