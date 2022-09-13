package com.on99ft;

import com.on99ft.domain.Offices;
import com.on99ft.service.OfficesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OfficesTest {
    @Autowired
    private OfficesService officesService;

    @Test
    void test1(){
        Offices offices =new Offices();
        officesService.insert(offices);
    }
    @Test
    void test(){
        List<Offices> offices = officesService.SelectAll();
        System.out.println("offices = " + offices);
    }
}
