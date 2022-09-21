package com.on99ft.service;

import com.on99ft.domain.Article;

import java.util.List;

public interface ArticleService {
    public boolean insert(Article a);
    public boolean delete(Article a);
    public boolean update(Article a);
    public Article selectId(Long id);
    public List<Article> selectAll();
    public Long countArticle();

    public List<Article> selectWithLimit(Long cur,Long size);

    public List<Article> LikeTitleOrText(Article a);
}
