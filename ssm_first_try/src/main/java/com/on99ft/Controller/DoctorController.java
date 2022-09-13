package com.on99ft.Controller;

import com.on99ft.domain.Doctor;
import com.on99ft.domain.Knowledge;
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

    /* todo 模糊查询细节: or 黑马p112 or https://baomidou.com/pages/10c804/

    @Requestmapping（value="/{category}/{brand}/{id},method=RequestMethod.POST）
public void getbyid(@PathVariable("category") String category
                    @PathVariable("brand") String brand
                    @PathVariable("id") String id){
                    //具体代码略
        }

//条件封装
QueryWrapper<FykUser> queryWrapper = new QueryWrapper<>();
queryWrapper.like(StringUtils.isNotBlank(user.getName()), "NAME", user.getName());
queryWrapper.like(user.getEnable() != null, "ENABLE", user.getEnable());
List<FykUser> userList = userDao.selectList(queryWrapper);

也就是说，调用queryWrapper的like方法就可以。

这里，like方法有三个参数：

第一个参数：该参数是一个布尔类型，只有该参数是true时，才将like条件拼接到sql中；本例中，如果name字段不为空，则拼接name字段的like查询条件；
第二个参数：该参数是数据库中的字段名；
第三个参数：该参数值字段值；
需要说明的是，这里的like查询是使用的默认方式，也就是说在查询条件的左右两边都有%：NAME = ‘%王%"；
 如果只需要在左边或者右边拼接%，可以使用likeLeft或者likeRight方法。
    */

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
