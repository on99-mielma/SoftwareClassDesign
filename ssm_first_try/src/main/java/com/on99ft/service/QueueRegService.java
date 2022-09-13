package com.on99ft.service;

import com.on99ft.domain.QueueReg;

import java.util.List;

public interface QueueRegService {
    public List<QueueReg> getAll();
    public QueueReg getById(Long id);
    public boolean save(QueueReg q);
    public boolean delete(QueueReg q);

    public boolean update(QueueReg q);

    public Long countQueue();
}
