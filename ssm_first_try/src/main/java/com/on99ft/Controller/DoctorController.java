package com.on99ft.Controller;

import com.on99ft.domain.Doctor;
import com.on99ft.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Doctor d){
        Boolean pd = doctorService.insert(d);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Doctor d){
        Boolean pd = doctorService.update(d);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }



    @GetMapping("/{id}")
    public Result getByid(@PathVariable Long id){
        Doctor k = doctorService.getById(id);
        Integer code = k!=null?Code.GET_OK:Code.GET_ERR;
        String msg = k!=null?"Yes":"No";
        return new Result(code,msg,k);
    }

    @GetMapping
    public Result getAll(){
        List<Doctor> knowledgeList = doctorService.getAll();
        Integer code = knowledgeList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = knowledgeList!=null?"Yes":"No";
        return new Result(code,msg,knowledgeList);
    }
    @GetMapping(value = "/gs")
    public Result getAllgs(){
        List<Doctor> knowledgeList = doctorService.getAll();
        for (Doctor d:
             knowledgeList) {
            if(d.getSkill()==null||("".equals(d.getSkill()))){
                d.setSkill("无");
            }
            if(d.getInfo()==null||("".equals(d.getInfo()))){
                d.setInfo("无");
            }
            if(d.getSkill().length()>=8){
                d.setSkill(d.getSkill().substring(0,8));
            }
            if(d.getInfo().length()>=8){
                d.setInfo(d.getInfo().substring(0,8));
            }
        }
        Integer code = knowledgeList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = knowledgeList!=null?"Yes":"No";
        return new Result(code,msg,knowledgeList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        Boolean pd = doctorService.delete(id);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",doctorService.countDoctor());
    }
}
