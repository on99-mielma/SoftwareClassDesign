package com.on99ft.Controller;

import com.on99ft.domain.QueueReg;
import com.on99ft.service.QueueRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping("/queuereg")
public class QueueRegController {
    @Autowired
    private QueueRegService queueRegService;

    @PostMapping("/insert")
    public Result save(@RequestBody QueueReg q){
        //phone
        String pattern = "^((13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8})|(\\d{3}-\\d{6,8})|(\\d{4}-\\d{6,8})$";
        //card number
        String pattern2 = "^([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3})|([1-9]\\d{5}(18|19|([2-3]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9xX])$";
        boolean pdPhone = (q.getPhone()==null||"".equals(q.getPhone()))?false:Pattern.matches(pattern,q.getPhone());
        boolean pdCardNumber = (q.getInfo()==null||"".equals(q.getInfo()))?false:Pattern.matches(pattern2,q.getInfo());
        if(!pdPhone||!pdCardNumber){
            return new Result(Code.SAVE_ERR,"格式检测到错误","格式检测到错误");
        }
        boolean pd = queueRegService.save(q);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd?"挂号成功！请凭身份证在医生值班时间段去医院诊治":"挂号失败，请检查重试",q);
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
