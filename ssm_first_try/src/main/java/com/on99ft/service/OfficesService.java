package com.on99ft.service;

import com.on99ft.domain.Offices;

import java.util.List;
//todo 注意在接口文件中可以通过Alt+Enter一键生成实现类
public interface OfficesService {
    public Offices SelectByName(String name);
    public List<Offices> SelectAll();

    public boolean update(Offices offices);
    public boolean insert(Offices offices);
    public boolean delete(Offices offices);

    public Offices selectById(Long id);

    public Long countOffices();
}
