package com.on99ft.Controller;


import com.on99ft.domain.Article;
import com.on99ft.domain.Offices;
import com.on99ft.service.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/offices")
public class OfficesController {
    @Autowired
    private OfficesService officesService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Offices offices){
        boolean pd = officesService.insert(offices);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Offices offices){
        boolean pd = officesService.update(offices);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @DeleteMapping
    public Result delete(@RequestBody Offices offices){
        boolean pd = officesService.delete(offices);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }

    @GetMapping("/{name}")
    public Result selectName(@PathVariable String name){
        Offices offices = officesService.SelectByName(name);
        Integer code = offices!=null?Code.GET_OK:Code.GET_ERR;
        String msg = offices!=null?"Successfully!":"NULL";
        return new Result(code,msg,offices,1);
    }
    @GetMapping("/id/{id}")
    public Result selectId(@PathVariable Long id){
        Offices offices = officesService.selectById(id);
        Integer code = offices!=null?Code.GET_OK:Code.GET_ERR;
        String msg = offices!=null?"Successfully!":"NULL";
        return new Result(code,msg,offices,1);
    }
    //todo 双击选中单词 三击选中整行
    @GetMapping
    public Result selectAll(){
        List<Offices> officesList = officesService.SelectAll();
        Integer code = officesList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = officesList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,officesList,1);
    }
    @GetMapping("/gs")
    public Result selectAllgs(){
        List<Offices> officesList = officesService.SelectAll();
        for (Offices o:
             officesList) {
            if(o.getOfficeInfo()==null||"".equals(o.getOfficeInfo())){
                o.setOfficeInfo("无");
            }
            if(o.getOfficeName()==null||"".equals(o.getOfficeName())){
                o.setOfficeName("未知科室");
            }
            if(o.getOfficeInfo().length()>=15){
                o.setOfficeInfo(o.getOfficeInfo().substring(0,15));
            }
        }
        Integer code = officesList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = officesList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,officesList,1);
    }
    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",officesService.countOffices());
    }
}
