package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.on99ft.dao.QueueRegDao;
import com.on99ft.domain.QueueReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueRegServiceImpl implements QueueRegService {

    @Autowired
    private QueueRegDao queueRegDao;

    public List<QueueReg> getAll() {
        QueryWrapper<QueueReg> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        return queueRegDao.selectList(queryWrapper);
    }

    public QueueReg getById(Long id) {
        return queueRegDao.selectById(id);
    }

    public boolean save(QueueReg q) {
        return queueRegDao.insert(q)>0;
    }

    public boolean delete(QueueReg q) {
        return queueRegDao.deleteById(q)>0;
    }

    public Long countQueue() {
        QueryWrapper<QueueReg> queryWrapper = new QueryWrapper<QueueReg>();
        return queueRegDao.selectCount(queryWrapper);
    }

    public boolean update(QueueReg q){
        return queueRegDao.updateById(q)>0;
    }
}
