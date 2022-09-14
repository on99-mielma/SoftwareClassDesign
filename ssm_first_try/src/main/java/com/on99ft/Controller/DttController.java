package com.on99ft.Controller;

import com.on99ft.domain.Dtt;
import com.on99ft.service.DttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/dtt")
public class DttController {
    @Autowired
    private DttService dttService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Dtt dtt){
        boolean pd = dttService.insert(dtt);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Dtt dtt){
        boolean pd = dttService.update(dtt);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @DeleteMapping
    public Result delete(@RequestBody Dtt dtt){
        boolean pd = dttService.delete(dtt);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }

    @GetMapping("/{id}")
    public Result selectId(@PathVariable Long id){
        Dtt dtt = dttService.selectOne(id);
        Integer code = dtt!=null?Code.GET_OK:Code.GET_ERR;
        String msg = dtt!=null?"Successfully!":"查询失败";
        return new Result(code,msg,dtt);
    }

    @GetMapping
    public Result selectAll(){
        List<Dtt> dttList = dttService.selectAll();
        Integer code = dttList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = dttList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,dttList);
    }
}
