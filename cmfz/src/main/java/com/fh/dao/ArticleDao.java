package com.fh.dao;

import com.fh.entity.Article;

import java.util.List;

public interface ArticleDao {
    //查询所有的文章
    public List<Article> selectAllArticle();
    //修改文章
    public void updateArticle(Article article);
    //添加文章
    public void insertArticle(Article article);
}
