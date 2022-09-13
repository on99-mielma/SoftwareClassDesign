package com.on99ft.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.on99ft.domain.Offices;
import org.apache.ibatis.annotations.Mapper;

//todo 注意命名方法选择驼峰命名法
@Mapper
public interface OfficesDao extends BaseMapper<Offices> {
}
