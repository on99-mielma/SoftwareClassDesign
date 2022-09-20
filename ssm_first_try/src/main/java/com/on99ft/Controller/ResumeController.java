package com.on99ft.Controller;

import com.on99ft.domain.Resume;
import com.on99ft.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Resume r){
        boolean pd = resumeService.insert(r);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam(value = "TheFile")MultipartFile TheFile,@RequestParam(value = "name",required = false) String name,@RequestParam(value = "gender",required = false) String gender,@RequestParam(value = "phoneNumber",required = false) String phoneNumber,@RequestParam(value = "cardNumber",required = false) String cardNumber){
        boolean pd =false;
        if(TheFile==null){
            return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
        }
            try {
                if (TheFile.getOriginalFilename().endsWith(".doc")||TheFile.getOriginalFilename().endsWith(".docx")) {
                    Resume resume = new Resume();
                    resume.setName(name!=null?name:"未填写");
                    resume.setGender(gender!=null?gender:"未填写");
                    resume.setPhoneNumber(phoneNumber!=null?phoneNumber:"未填写");
                    resume.setCardNumber(cardNumber!=null?cardNumber:"未填写");
                    resume.setFile(TheFile.getBytes());
                    pd=resumeService.insert(resume);
                    return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
                }
                return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @DeleteMapping()
    public Result deleteById(@RequestBody Resume r){
        boolean pd = resumeService.delete(r);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Resume r){
        boolean pd = resumeService.update(r);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @GetMapping("/{id}")
    public Result selectId(@PathVariable Long id){
        Resume resume = resumeService.selectId(id);
        Integer code = resume!=null?Code.GET_OK:Code.GET_ERR;
        String msg = resume!=null?"Successfully!":"查询失败";
        return new Result(code,msg,resume);
    }

    @GetMapping
    public Result selectAll(){
        List<Resume> resumeList = resumeService.selectAll();
        Integer code = resumeList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = resumeList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,resumeList);
    }

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",resumeService.countResume());
    }
}
