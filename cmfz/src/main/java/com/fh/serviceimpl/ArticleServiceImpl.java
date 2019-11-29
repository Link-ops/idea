package com.fh.serviceimpl;

import com.fh.dao.ArticleDao;
import com.fh.entity.Article;
import com.fh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Article> selectAllArticle() {
        return articleDao.selectAllArticle();
    }

    @Override
    public String updateArticle(Article article) {
        articleDao.updateArticle(article);
        return article.getId();
    }

    @Override
    public String insertArticle(Article article) {
        String s = UUID.randomUUID().toString();
        article.setId(s);
        article.setCreateTime(new Date());
        articleDao.insertArticle(article);
        System.out.println(article);
        return s;
    }
}
