package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.on99ft.dao.BillboardDao;
import com.on99ft.domain.Billboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillboardServiceImpl implements BillboardService {
    @Autowired
    private BillboardDao billboardDao;


    public boolean insert(Billboard n) {
        return billboardDao.insert(n)>0;
    }

    public boolean delete(Billboard n) {
        return billboardDao.deleteById(n)>0;
    }

    public boolean update(Billboard n) {
        return billboardDao.updateById(n)>0;
    }

    public Billboard selectById(Long id) {
        return billboardDao.selectById(id);
    }

    public List<Billboard> selectAll() {
        QueryWrapper<Billboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        return billboardDao.selectList(queryWrapper);
    }

    public Long countBillboard() {
        QueryWrapper<Billboard> q = new QueryWrapper<Billboard>();
        return billboardDao.selectCount(q);
    }

    public List<Billboard> selectWithLimit(Long cur, Long size) {
        QueryWrapper<Billboard> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        IPage<Billboard> page = billboardDao.selectPage(new Page<>(cur,size),queryWrapper);
        return page.getRecords();
    }
}
