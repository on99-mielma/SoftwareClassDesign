package com.on99ft.service;

import com.on99ft.domain.Dtt;

import java.util.List;

public interface DttService {
    public boolean insert(Dtt dtt);
    public boolean delete(Dtt dtt);
    public boolean update(Dtt dtt);
    public Dtt selectOne(Long id);
    public List<Dtt> selectAll();
}
