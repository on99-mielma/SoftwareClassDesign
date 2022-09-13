package com.on99ft.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.on99ft.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    /*@Insert("insert into user (uName,uPw,uSchool,uDepartment) values(#{uName},#{uPw},#{uSchool},#{uDepartment})")
    public int save(User user);

    @Update("update user set uName=#{uName},uPw=#{uPw},uSchool=#{uSchool},uDepartment=#{uDepartment} where uId = #{uId}")
    public int update(User user);

    @Delete("delete from user where uId=#{id}")
    public int delete(Integer id);
    @Select("select * from user")
    public List<User> selectAll();

    @Select("select * from user where uId = #{uId}")
    public User selectId(Integer id);*/


}
