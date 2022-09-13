package com.on99ft.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.on99ft.domain.Knowledge;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KnowledgeDao extends BaseMapper<Knowledge> {
}
