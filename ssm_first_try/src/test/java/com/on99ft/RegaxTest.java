package com.on99ft;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.on99ft.dao.QueueRegDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@SpringBootTest
public class RegaxTest {
    @Autowired
    private QueueRegDao queueRegDao;

    @Test
    void Test0(){
        String pattern = "^((13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8})|(\\d{3}-\\d{6,8})|(\\d{4}-\\d{6,8})$";
        String phone1 = "18938507963";
        String phone2 = "0769-85553321";
        String phone3 = "216491654";
        Boolean pd1 = Pattern.matches(pattern,phone1);
        Boolean pd2 = Pattern.matches(pattern,phone2);
        Boolean pd3 = Pattern.matches(pattern,phone3);
        System.out.println("pd1 = " + pd1);
        System.out.println("pd2 = " + pd2);
        System.out.println("pd3 = " + pd3);
        String pattern2 = "^([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3})|([1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9xX])$";
        boolean cn1 = Pattern.matches(pattern2,"441900207759x");
        boolean cn2 = Pattern.matches(pattern2,"441900200110177519");
        System.out.println("cn1 = " + cn1);
        System.out.println("cn2 = " + cn2);
    }
}
