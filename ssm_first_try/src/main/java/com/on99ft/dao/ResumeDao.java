package com.on99ft.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.on99ft.domain.Resume;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResumeDao extends BaseMapper<Resume> {
}
