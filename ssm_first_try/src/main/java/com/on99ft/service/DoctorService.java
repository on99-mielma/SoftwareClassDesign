package com.on99ft.service;

import com.on99ft.domain.Doctor;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    public Doctor getById(Long id);
    public List<Doctor> getAll();
    public Boolean insert(Doctor d);
    public Boolean delete(Long id);
    public Boolean update(Doctor d);

    public Long countDoctor();

    public List<Doctor> LikeNameAndOffice(Doctor d);

    public List<Doctor> WhereOffice(String o);

    public List<Doctor> LikeSkillandInfo(Doctor d);

    public List<Doctor> LikeExceptId(Doctor d);
}
