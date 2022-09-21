package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.on99ft.dao.OfficesDao;
import com.on99ft.domain.Offices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfficesServiceImpl implements OfficesService {

    @Autowired
    private OfficesDao officesDao;

    public Offices SelectByName(String name) {
        LambdaQueryWrapper<Offices> lqw = new LambdaQueryWrapper<Offices>();
        lqw.eq(Offices::getOfficeName,name);
        Offices offices = officesDao.selectOne(lqw);
        return offices;
    }

    public List<Offices> SelectAll() {
        QueryWrapper<Offices> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("officeName");
        return officesDao.selectList(queryWrapper);
    }

    public Long countOffices() {
        QueryWrapper<Offices>  queryWrapper = new QueryWrapper<Offices>();
        return officesDao.selectCount(queryWrapper);
    }

    public boolean update(Offices offices) {
        return officesDao.updateById(offices)>0;
    }

    public boolean insert(Offices offices) {
        return officesDao.insert(offices)>0;
    }

    public boolean delete(Offices offices) {
        return officesDao.deleteById(offices)>0;
    }

    public Offices selectById(Long id) {
        return officesDao.selectById(id);
    }

    public List<Offices> LikeName(Offices offices) {
        QueryWrapper<Offices> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(offices.getOfficeName()),"officeName",offices.getOfficeName());
        return officesDao.selectList(queryWrapper);
    }
}
