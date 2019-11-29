package com.fh.controller;

import com.fh.entity.Article;
import com.fh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Scope(value = "prototype")
@RequestMapping("article")
@ResponseBody
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("selectAll")
    public List<Article> selectAllArticle() {
        return articleService.selectAllArticle();
    }

    @RequestMapping("add")
    public void insertArticle(Article article) {
        articleService.insertArticle(article);
    }

    @RequestMapping("update")
    public void updateArticle(Article article) {
        articleService.updateArticle(article);
    }
}
