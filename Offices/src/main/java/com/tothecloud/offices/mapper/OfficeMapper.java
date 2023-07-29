package com.tothecloud.offices.mapper;


import com.tothecloud.offices.domain.Office;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OfficeMapper {
    @Select("select id,officeName,officeInfo from offices order by id")
    List<Office> selectAll();

    @Select("select id,officeName,officeInfo from offices where id = #{id}")
    Office selectOneByid(@Param("id") Long id);

    @Insert("""
            INSERT INTO offices (id, officeName, officeInfo)
             VALUES (#{id},#{officeName},#{officeInfo})
            """)
    int insertOne(Office office);


    @Delete("""
            DELETE FROM offices WHERE id = #{id}
            """)
    int deleteOneByid(@Param("id") Long id);


    /**
     * UPDATE tothecloud.notification t SET t.title = 'titleeee', t.text = 'eeeeeeeee', t.author = 'me' WHERE t.id = 7017185669583867904
     *
     * @param office
     * @return update count
     */
    @Update("""
            UPDATE offices t SET t.officeName = #{officeName},t.officeInfo = #{officeInfo} WHERE t.id = #{id}
            """)
    int updateOneByDomain(Office office);
}
