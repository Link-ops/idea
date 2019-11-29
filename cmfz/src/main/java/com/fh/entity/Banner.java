package com.fh.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@ExcelTarget(value = "轮播图")
public class Banner implements Serializable {
    @Excel(name = "轮播图编号",needMerge = true)
    private String id;
    @Excel(name = "轮播图标题",needMerge = true)
    private String title;
    @Excel(name = "轮播图描述",needMerge = true)
    private String description;
    @Excel(name = "创建时间",format = "yyyy-MM-dd",needMerge = true)
    private Date time;
    @Excel(replace = {"展示_y","不展示_n"},name = "轮播图状态",needMerge = true)
    private String state;
    @Excel(name = "轮播图图片",needMerge = true,type = 2,width = 50,height = 35)
    private String img;
}
