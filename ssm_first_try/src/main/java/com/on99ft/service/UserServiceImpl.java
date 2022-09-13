package com.on99ft.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.on99ft.dao.UserDao;
import com.on99ft.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserDao userDao;


    public boolean save(User user) {
        return userDao.insert(user) > 0;
    }

    public boolean update(User user) {
        return userDao.updateById(user) > 0;
    }

    public boolean delete(Long id) {
        return userDao.deleteById(id) > 0;
    }
    public User selectId(Long id){
        return userDao.selectById(id);
    }

    public List<User> selectAll() {
        return userDao.selectList(null);
    }

    public User dologin(User user) {
        /*LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getuName,"呃呃").eq(User::getuPw,"123456");
        User user = userDao.selectOne(lqw);
        System.out.println("user = " + user);*/
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getuName,user.getuName()).eq(User::getuPw,user.getuPw());
        /*System.out.println("user = " + user);*/
        User w = userDao.selectOne(lqw);
        return w;
    }

    public Long countUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        return userDao.selectCount(queryWrapper);
    }
}
