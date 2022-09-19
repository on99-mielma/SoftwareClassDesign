package com.on99ft.service;

import com.on99ft.domain.Billboard;

import java.util.List;

public interface BillboardService {
    public boolean insert(Billboard n);
    public boolean delete(Billboard n);
    public boolean update(Billboard n);
    public Billboard selectById(Long id);
    public List<Billboard> selectAll();
    public Long countBillboard();
    public List<Billboard> selectWithLimit(Long cur, Long size);
}
