package com.fh.service;

import com.fh.entity.Article;

import java.util.List;

public interface ArticleService {
    //查询所有的文章
    public List<Article> selectAllArticle();
    //修改文章
    public String updateArticle(Article article);
    //添加文章
    public String insertArticle(Article article);
}
