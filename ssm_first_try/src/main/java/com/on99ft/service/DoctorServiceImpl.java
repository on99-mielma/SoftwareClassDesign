package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.on99ft.dao.DoctorDao;
import com.on99ft.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorDao doctorDao;

    public Doctor getById(Long id) {
        return doctorDao.selectById(id);
    }

    public List<Doctor> getAll() {
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id","office");
        return doctorDao.selectList(null);
    }

    public Boolean insert(Doctor d) {
        return doctorDao.insert(d)>0;
    }

    public Boolean delete(Long id) {
        return doctorDao.deleteById(id)>0;
    }

    public Boolean update(Doctor d) {
        return doctorDao.updateById(d)>0;
    }

    public Long countDoctor() {
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<Doctor>();
        return doctorDao.selectCount(queryWrapper);
    }
}
