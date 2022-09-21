package com.on99ft.Controller;


import com.on99ft.domain.Article;
import com.on99ft.domain.Doctor;
import com.on99ft.domain.Dtt;
import com.on99ft.domain.Offices;
import com.on99ft.service.DoctorService;
import com.on99ft.service.DttService;
import com.on99ft.service.OfficesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/offices")
public class OfficesController {
    @Autowired
    private OfficesService officesService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DttService dttService;

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
        Integer code = offices!=null?Code.GET_OFFICES_OK:Code.GET_OFFICES_ERR;
        String msg = offices!=null?"Successfully!":"NULL";
        return new Result(code,msg,offices);
    }
    @GetMapping("/id/{id}")
    public Result selectId(@PathVariable Long id){
        Offices offices = officesService.selectById(id);
        Integer code = offices!=null?Code.GET_OFFICES_OK:Code.GET_OFFICES_ERR;
        String msg = offices!=null?"Successfully!":"NULL";
        if(offices==null){
            return new Result(code,msg,offices);
        }
        Map<Integer,List<Doctor>> TMapIL = new HashMap<>();
        List<Doctor> doctorList = doctorService.WhereOffice(offices.getOfficeName());
        if(doctorList==null){
            return new Result(code,msg,offices);
        }
        else{
            for(Doctor d: doctorList) {
                d.setInfo("null");
                d.setSkill("null");
                d.setOffice("null");
                if(TMapIL.containsKey(0)){
                    TMapIL.get(0).add(d);
                }
                else {
                    List<Doctor> doctors = new ArrayList<>();
                    doctors.add(d);
                    TMapIL.put(0,doctors);
                }
            }
        }
        offices.setDoctorInOffice(TMapIL);
        return new Result(code,msg,offices);
    }
    //todo 双击选中单词 三击选中整行
    @GetMapping
    public Result selectAll(){
        List<Offices> officesList = officesService.SelectAll();
        Integer code = officesList!=null?Code.GET_OFFICES_OK:Code.GET_OFFICES_ERR;
        String msg = officesList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,officesList);
    }
    @GetMapping("/gs")
    public Result selectAllgs(){
        List<Offices> officesList = officesService.SelectAll();
        Integer code = officesList!=null?Code.GET_OFFICES_OK:Code.GET_OFFICES_ERR;
        String msg = officesList!=null?"Successfully!":"查询失败";
        if(officesList==null){
            return new Result(code,msg,null);
        }
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
        return new Result(code,msg,officesList);
    }

    @GetMapping("/2d/{size}")
    public Result select2D(@PathVariable Long size){//size表示一组多少个
        List<Offices> officesList = officesService.SelectAll();
        Integer code = officesList!=null?Code.GET_OFFICES_OK:Code.GET_OFFICES_ERR;
        String msg = officesList!=null?"Successfully!":"查询失败";
        if(officesList==null){
            return new Result(code,msg,null);
        }
        ArrayList<List<Offices>> resultList = new ArrayList<>();
        Long i=0L;
        List<Offices> tempList = new ArrayList<>();
        for (Offices w: officesList) {
            if(i.equals(size)){
                i=0L;
                resultList.add(tempList);
                tempList=new ArrayList<>();
            }
            i+=1L;
            tempList.add(w);
        }
        if(!tempList.isEmpty()){
            resultList.add(tempList);
        }
        return new Result(code,msg,resultList);
    }

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",officesService.countOffices());
    }

    @GetMapping("LOAD")
    public Result LikeOfficeAndDoctor(@RequestBody Offices offices){//0-6早上 10-16下午 20-26晚上
        List<Offices> officesList = officesService.LikeName(offices);
        if(officesList==null){
            return new Result(Code.GET_OFFICES_ERR,"关键词查询无结果",officesList);
        }
        for (Offices o: officesList) {
            Map<Integer,List<Doctor>> TMapIL = new HashMap<>();
            List<Doctor> doctorList = doctorService.WhereOffice(o.getOfficeName());
            if(doctorList==null){
                continue;
            }
            else{
                for (Doctor d: doctorList) {
                    Dtt dtt = dttService.selectOne(d.getId());
                    String[] morning = dtt.getMorning().split("/");
/*                    System.out.println("morning = " + morning);*/
                    for (int i = 0; i < morning.length; i++) {
                        String tmp = morning[i];
                        if("0".equals(tmp)){
                            continue;
                        }
                        else{
                            if(TMapIL.containsKey(i)){
                                TMapIL.get(i).add(d);
                            }
                            else {
                                List<Doctor> doctors = new ArrayList<>();
                                doctors.add(d);
                                TMapIL.put(i,doctors);
                            }
                        }
                    }
                    String[] afternoon = dtt.getAfternoon().split("/");
                    for (int i = 0; i < afternoon.length; i++) {
                        String tmp = afternoon[i];
                        if("0".equals(tmp)){
                            continue;
                        }
                        else{
                            if(TMapIL.containsKey(i+10)){
                                TMapIL.get(i+10).add(d);
                            }
                            else {
                                List<Doctor> doctors = new ArrayList<>();
                                doctors.add(d);
                                TMapIL.put(i+10,doctors);
                            }
                        }
                    }
                    String[] night = dtt.getNight().split("/");
                    for (int i = 0; i < night.length; i++) {
                        String tmp = night[i];
                        if("0".equals(tmp)){
                            continue;
                        }
                        else{
                            if(TMapIL.containsKey(i+20)){
                                TMapIL.get(i+20).add(d);
                            }
                            else {
                                List<Doctor> doctors = new ArrayList<>();
                                doctors.add(d);
                                TMapIL.put(i+20,doctors);
                            }
                        }
                    }
                }
            }
        o.setDoctorInOffice(TMapIL);
        }
        return new Result(Code.GET_OFFICES_OK,"结果如下",officesList);
    }
}
