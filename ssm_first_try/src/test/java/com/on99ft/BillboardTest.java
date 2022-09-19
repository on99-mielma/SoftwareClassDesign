package com.on99ft;

import com.on99ft.domain.Billboard;
import com.on99ft.service.BillboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BillboardTest {

    @Autowired
    private BillboardService billboardService;

    @Test
    void test0(){
        Billboard billboard = new Billboard();
        billboard.setTitle("");
        billboard.setText("");
        LocalDateTime ldt = LocalDateTime.now();
        billboard.setDate(ldt);
        billboard.setAuthor("本站原创");
        billboardService.insert(billboard);
    }

    @Test
    void test1(){
        billboardService.selectAll().forEach(System.out::println);
    }
}
