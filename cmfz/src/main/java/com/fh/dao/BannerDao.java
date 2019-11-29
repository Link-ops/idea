package com.fh.dao;

import com.fh.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //查询所有轮播图信息
    public List<Banner> selectAllBanner();
    //添加一个轮播图的信息
    public void insertBanner(Banner banner);
    //查询单个轮播图的信息
    public Banner selectOneBanner(String id);
    //修改单个轮播图的信息
    public void updateBanner(Banner banner);
    //删除一个轮播图信息
    public void deleteOneBanner(String id);
    //修改一个轮播图的路径
    public void updateBannerImg(@Param("id") String id,@Param("img") String img);
    //批量删除
    public void deleteSomeBanner(String[] ids);
}
