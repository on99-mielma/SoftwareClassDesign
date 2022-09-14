package com.on99ft;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.on99ft.dao.DoctorDao;
import com.on99ft.domain.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LikeTest {

    @Autowired
    private DoctorDao doctorDao;
    @Test
    void Test1(){
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        Doctor doctor = new Doctor();
        doctor.setOffice("内科");
        queryWrapper.like(StringUtils.isNotBlank(doctor.getOffice()),"office",doctor.getOffice());
        List<Doctor> doctorList = doctorDao.selectList(queryWrapper);
        for (Doctor d:
             doctorList) {
            System.out.println("d = " + d);
        }
    }
}
/* todo 模糊查询细节: or 黑马p112 or https://baomidou.com/pages/10c804/

    @Requestmapping（value="/{category}/{brand}/{id},method=RequestMethod.POST）
public void getbyid(@PathVariable("category") String category
                    @PathVariable("brand") String brand
                    @PathVariable("id") String id){
                    //具体代码略
        }

//条件封装
QueryWrapper<FykUser> queryWrapper = new QueryWrapper<>();
queryWrapper.like(StringUtils.isNotBlank(user.getName()), "NAME", user.getName());
queryWrapper.like(user.getEnable() != null, "ENABLE", user.getEnable());
List<FykUser> userList = userDao.selectList(queryWrapper);

也就是说，调用queryWrapper的like方法就可以。

这里，like方法有三个参数：

第一个参数：该参数是一个布尔类型，只有该参数是true时，才将like条件拼接到sql中；本例中，如果name字段不为空，则拼接name字段的like查询条件；
第二个参数：该参数是数据库中的字段名；
第三个参数：该参数值字段值；
需要说明的是，这里的like查询是使用的默认方式，也就是说在查询条件的左右两边都有%：NAME = ‘%王%"；
 如果只需要在左边或者右边拼接%，可以使用likeLeft或者likeRight方法。
    */