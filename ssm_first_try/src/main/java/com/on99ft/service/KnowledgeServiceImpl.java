package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.on99ft.dao.KnowledgeDao;
import com.on99ft.domain.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService{
    @Autowired
    private KnowledgeDao knowledgeDao;

    public List<Knowledge> getAll() {
        QueryWrapper<Knowledge> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return knowledgeDao.selectList(queryWrapper);
    }

    public Knowledge getById(Long id){
        return knowledgeDao.selectById(id);
    }

    public Integer insert(Knowledge k) {
        return knowledgeDao.insert(k);
    }

    @Override
    public Integer update(Knowledge k) {
        return knowledgeDao.updateById(k);
    }

    public Integer delete(Long id){
        return knowledgeDao.deleteById(id);
    }

    public Long countKnowledge() {
        QueryWrapper<Knowledge> q = new QueryWrapper<>();
        return knowledgeDao.selectCount(q);
    }

    public List<Knowledge> selectWithLimit(Long cur, Long size) {
        QueryWrapper<Knowledge> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<Knowledge> page = knowledgeDao.selectPage(new Page<Knowledge>(cur,size),queryWrapper);
        return page.getRecords();
    }
}
