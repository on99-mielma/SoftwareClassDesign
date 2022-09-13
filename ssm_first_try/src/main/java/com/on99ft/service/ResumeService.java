package com.on99ft.service;

import com.on99ft.domain.Resume;

import java.util.List;

public interface ResumeService {
    public boolean insert(Resume r);
    public boolean delete(Resume r);
    public boolean update(Resume r);
    public Resume selectId(Long id);
    public List<Resume> selectAll();

    public Long countResume();
}
