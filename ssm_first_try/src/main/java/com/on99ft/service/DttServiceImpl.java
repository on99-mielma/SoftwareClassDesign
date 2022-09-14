package com.on99ft.service;

import com.on99ft.dao.DttDao;
import com.on99ft.domain.Dtt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DttServiceImpl implements DttService {

    @Autowired
    private DttDao dttDao;

    public boolean insert(Dtt dtt) {
        return dttDao.insert(dtt)>0;
    }

    public boolean delete(Dtt dtt) {
        return dttDao.deleteById(dtt)>0;
    }

    public boolean update(Dtt dtt) {
        return dttDao.updateById(dtt)>0;
    }

    public Dtt selectOne(Long id) {
        return dttDao.selectById(id);
    }

    public List<Dtt> selectAll() {
        return dttDao.selectList(null);
    }
}
