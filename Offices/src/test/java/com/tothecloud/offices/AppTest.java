package com.tothecloud.offices;


import com.tothecloud.offices.domain.Office;
import com.tothecloud.offices.domain.OfficesLite;
import com.tothecloud.offices.mapper.OfficeMapper;
import com.tothecloud.offices.services.OfficesService;
import com.tothecloud.offices.utils.ConstantUtils;
import com.tothecloud.offices.utils.NullUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Unit test for simple OfficesApplication.
 */
@SpringBootTest
public class AppTest {
    @Autowired
    private OfficeMapper officeMapper;

    @Autowired
    private OfficesService officesService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    String keylite = ConstantUtils.PROJECT_PREFIX + ConstantUtils.OFFLITE_PREFIX;

    @Test
    void selectALL(){
        List<Office> officeList = officeMapper.selectAll();
        System.out.println(officeList);
        Assert.isTrue(officeList.size() == 17,"总数目不对!(硬编码 请更新)");
    }

    @Test
    void selectOneByID(){
        Office one = officeMapper.selectOneByid(1572532959297896450L);
        Assert.isTrue(one.getOfficeName().equals("皮肤科"),"OfficeName match false");
    }

    @Test
    void selectNoneID(){
        Office one = officeMapper.selectOneByid(110L);
        Assert.isTrue(null == one,"检查不存在的key");
    }
    @Test
    void insertTest(){
        Office office = new Office();
        office.setOfficeName("111");
        int i = officeMapper.insertOne(NullUtils.fixNull(office));
        Assert.isTrue(i == 1, "插入数目不对");
    }

    @Test
    void updateTest(){
        Office office = new Office();
        office.setId(7017225572870918144L);
        office.setOfficeInfo("fuck u ");
        int i = officeMapper.updateOneByDomain(office);
        Assert.isTrue(i == 1, "成功数目不对");
    }

    @Test
    void selectLiteAll(){
//        List<Office> officesList = officesService.getOfficesList();
//        System.out.println(officesList);
        List<OfficesLite> officesLiteList = officesService.getOfficesLiteList();
        System.out.println(officesLiteList);

        String[] namelist = officesLiteList.stream().map(OfficesLite::getOfficename).distinct().toArray(String[]::new);
        System.out.println(Arrays.toString(namelist));
        Long add = redisTemplate.opsForSet().add(keylite, namelist);
        System.out.println(add);
        Set<String> members = redisTemplate.opsForSet().members(keylite);
        System.out.println(members);
    }
    @Test
    void isMember(){
        Boolean member = redisTemplate.opsForSet().isMember(keylite, "儿科");
        Assert.isTrue(Boolean.TRUE.equals(member),"查询居然错误");
        Boolean member2 = redisTemplate.opsForSet().isMember(keylite, "二科");
        Assert.isTrue(Boolean.FALSE.equals(member2),"查询居然错误");
    }
    @Test
    void tryUpdate(){
        String myvalue = "namelist";
        Long add = redisTemplate.opsForSet().add(keylite, myvalue);
        System.out.println(add);
        Boolean member = redisTemplate.opsForSet().isMember(keylite, myvalue);
        System.out.println(member);
        Long remove = redisTemplate.opsForSet().remove(keylite, myvalue);
        System.out.println(remove);
    }
}
