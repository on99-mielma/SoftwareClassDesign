package com.on99ft;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.on99ft.dao.UserDao;
import com.on99ft.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PageTest {
    @Autowired
    private UserDao userDao;

    @Test
    void testPage1(){
        //todo 分页实例
        IPage page = new Page(1,2);
        userDao.selectPage(page,null);
        System.out.println("当前页码值"+page.getCurrent());
        System.out.println("每页显示数"+page.getSize());
        System.out.println("一共多少页"+page.getPages());
        System.out.println("一共多少条"+page.getTotal());
        System.out.println("页数据"+page.getRecords());
    }

    @Test
    void selectSome(){
        //todo 查询想要的字段
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.select(User::getuId,User::getuName,User::getuPw);
        List<User> userList = userDao.selectList(lqw);
        System.out.println("userList = " + userList);
    }

    @Test
    void equalTest(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getuName,"呃呃").eq(User::getuPw,"123456");
        User user = userDao.selectOne(lqw);
        System.out.println("user = " + user);
        //todo 登入验证 null即错误 黑马p113即便加入了select=false也能查询.
    }

    @Test
    void countTest(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        Long ans = userDao.selectCount(queryWrapper);
        System.out.println("ans = " + ans);
    }
}
