package com.on99ft;

import com.on99ft.domain.User;
import com.on99ft.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SsmFirstTryApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        /*User u = new User();
        u.setuId(1);*/
        User user = userService.selectId(1L);
        System.out.println(user);
    }

    @Test
    void test2(){
        List<User> userList = userService.selectAll();
        System.out.println("userList = " + userList);
    }

    @Test
    void test3(){
        User user = new User();
        user.setuDepartment("?");
        user.setuName("?");
        user.setuPw("?");
        user.setuSchool("?");
        userService.save(user);
    }

    @Test
    void test4(){
        User user = new User();
        user.setuId(9L);
        user.setuDepartment("???");
        user.setuName("??");
        user.setuPw("??");
        userService.update(user);
    }


}
