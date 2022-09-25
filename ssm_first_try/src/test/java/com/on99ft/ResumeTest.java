package com.on99ft;

import com.on99ft.domain.Resume;
import com.on99ft.service.ResumeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@SpringBootTest
public class ResumeTest {
    @Autowired
    private ResumeService resumeService;

    /*@Test
    void test0() {
        Resume resume = resumeService.selectId(1572181320401563649L);
        String filepath="D:\\作业论文\\计算机网络\\111.docx";
        File file  = new File(filepath);
        if(file.exists()){
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(resume.getFile(),0,resume.getFile().length);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }*/
}
