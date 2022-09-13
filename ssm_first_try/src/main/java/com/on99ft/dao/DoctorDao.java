package com.on99ft.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.on99ft.domain.Doctor;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DoctorDao extends BaseMapper<Doctor> {
}
