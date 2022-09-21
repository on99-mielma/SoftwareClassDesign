package com.on99ft.service;

import com.on99ft.domain.Article;
import com.on99ft.domain.Knowledge;

import java.util.List;

public interface KnowledgeService {

    public Knowledge getById(Long id);
    public List<Knowledge> getAll();
    public Integer insert(Knowledge k);
    public Integer update(Knowledge k);

    public Integer delete(Long id);

    public Long countKnowledge();

    public List<Knowledge> selectWithLimit(Long cur,Long size);

    public List<Knowledge> LikeTitleOrText(Knowledge k);
}
