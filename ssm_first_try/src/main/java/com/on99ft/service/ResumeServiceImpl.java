package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.on99ft.dao.ResumeDao;
import com.on99ft.domain.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    public boolean insert(Resume r) {
        return resumeDao.insert(r)>0;
    }

    public boolean delete(Resume r) {
        return resumeDao.deleteById(r)>0;
    }

    public Resume selectId(Long id) {
        return resumeDao.selectById(id);
    }

    public List<Resume> selectAll() {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return resumeDao.selectList(queryWrapper);
    }

    public Long countResume() {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<Resume>();
        return resumeDao.selectCount(queryWrapper);
    }

    public boolean update(Resume r) {
        return resumeDao.updateById(r)>0;
    }
}
