package com.on99ft.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.on99ft.domain.Billboard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillboardDao extends BaseMapper<Billboard> {
}
