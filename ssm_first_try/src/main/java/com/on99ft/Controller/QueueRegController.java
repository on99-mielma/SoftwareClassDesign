package com.on99ft.Controller;

import com.on99ft.domain.QueueReg;
import com.on99ft.service.QueueRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/queuereg")
public class QueueRegController {
    @Autowired
    private QueueRegService queueRegService;

    @PostMapping("/insert")
    public Result save(@RequestBody QueueReg q){
        boolean pd = queueRegService.save(q);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,q);
    }

    @PutMapping
    public Result update(@RequestBody QueueReg q){
        boolean pd =queueRegService.update(q);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Long id){
        QueueReg queueReg = queueRegService.getById(id);
        Integer code = queueReg!=null?Code.GET_OK:Code.GET_ERR;
        String msg = queueReg!=null?"Successfully!":"查询失败";
        return new Result(code,msg,queueReg);
    }

    @DeleteMapping
    public Result deleteById(@RequestBody QueueReg queueReg){
        boolean pd = queueRegService.delete(queueReg);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }

    @GetMapping
    public Result selectAll(){
        List<QueueReg> queueRegList = queueRegService.getAll();
        Integer code = queueRegList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = queueRegList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,queueRegList);
    }
    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",queueRegService.countQueue());
    }
}
