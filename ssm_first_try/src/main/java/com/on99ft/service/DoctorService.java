package com.on99ft.service;

import com.on99ft.domain.Doctor;

import java.util.List;

public interface DoctorService {
    public Doctor getById(Long id);
    public List<Doctor> getAll();
    public Boolean insert(Doctor d);
    public Boolean delete(Long id);
    public Boolean update(Doctor d);

    public Long countDoctor();
}
