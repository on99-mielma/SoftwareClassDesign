package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.on99ft.dao.ArticleDao;
import com.on99ft.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public boolean insert(Article a) {
        return articleDao.insert(a)>0;
    }

    public boolean delete(Article a) {
        return articleDao.deleteById(a)>0;
    }

    public boolean update(Article a) {
        return articleDao.updateById(a)>0;
    }

    public Article selectId(Long id) {
        return articleDao.selectById(id);
    }

    public List<Article> selectAll() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        return articleDao.selectList(queryWrapper);
    }

    public Long countArticle() {
        QueryWrapper<Article> q = new QueryWrapper<Article>();
        return articleDao.selectCount(q);
    }

    public List<Article> selectWithLimit(Long cur, Long size) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        IPage<Article> page = articleDao.selectPage(new Page<>(cur,size),queryWrapper);
        return page.getRecords();
    }
}
