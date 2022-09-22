package com.on99ft.Controller;

import com.on99ft.domain.Doctor;
import com.on99ft.domain.Dtt;
import com.on99ft.domain.Offices;
import com.on99ft.service.DoctorService;
import com.on99ft.service.DttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DttService dttService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Doctor d){
        Boolean pd = doctorService.insert(d);
        Dtt dtt = new Dtt();
        Boolean pd2 = false;
        if(pd){
            dtt.setId(d.getId());
            pd2 = dttService.insert(dtt);
            return new Result(pd2?Code.SAVE_OK:Code.SAVE_ERR,"DOCTOR+DTT status:",pd2);
        }
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Doctor d){
        Boolean pd = doctorService.update(d);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }



    @GetMapping("/{id}")
    public Result getByid(@PathVariable Long id){
        Doctor doc = doctorService.getById(id);
        Integer code = doc!=null?Code.GET_DOCTOR_OK:Code.GET_DOCTOR_ERR;
        String msg = doc!=null?"Yes":"No";
        if(doc==null){
            return new Result(code,msg,doc);
        }
        Dtt dtt = dttService.selectOne(id);
        String[] morning = dtt.getMorning().split("/");
        String[] afternoon = dtt.getAfternoon().split("/");
        String[] night = dtt.getNight().split("/");
        doc.setMorning(morning);
        doc.setAfternoon(afternoon);
        doc.setNight(night);
        return new Result(code,msg,doc);
    }

    @GetMapping
    public Result getAll(){
        List<Doctor> doctorList = doctorService.getAll();
        Integer code = doctorList!=null?Code.GET_DOCTOR_OK:Code.GET_DOCTOR_ERR;
        String msg = doctorList!=null?"Yes":"No";
        if(doctorList==null){
            return new Result(code,msg,doctorList);
        }
        for (Doctor doc: doctorList) {
            Dtt dtt = dttService.selectOne(doc.getId());
            String[] morning = dtt.getMorning().split("/");
            String[] afternoon = dtt.getAfternoon().split("/");
            String[] night = dtt.getNight().split("/");
            doc.setMorning(morning);
            doc.setAfternoon(afternoon);
            doc.setNight(night);
        }
        return new Result(code,msg,doctorList);
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
        Integer code = knowledgeList!=null?Code.GET_DOCTOR_OK:Code.GET_DOCTOR_ERR;
        String msg = knowledgeList!=null?"Yes":"No";
        return new Result(code,msg,knowledgeList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        /*Boolean pd = doctorService.delete(id);
        Dtt dtt = new Dtt();
        Boolean pd2 = false;
        if(pd){
            Dtt tmp = dttService.selectOne(id);
            if(tmp==null){
                return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,"noDTT",pd);
            }
            else{
                dtt.setId(id);
                pd2 = dttService.delete(dtt);
                return new Result(pd2?Code.DELETE_OK:Code.DELETE_ERR,"DTT status:",pd2);
            }
        }
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);*/
        Dtt dtt = new Dtt();
        Boolean pd2 = false;//Dtt pd
        Boolean pd =false;//Doctor pd
        Dtt tmp = dttService.selectOne(id);
        if(tmp==null){
            return new Result(pd2?Code.DELETE_OK:Code.DELETE_ERR,"noDTT",pd2);
        }
        else{
            dtt.setId(id);
            pd2 = dttService.delete(dtt);
            if(pd2){
                pd = doctorService.delete(id);
                return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);
            }
            return new Result(pd2?Code.DELETE_OK:Code.DELETE_ERR,"dtt right doctor error",pd2);
        }
    }

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",doctorService.countDoctor());
    }

    @PostMapping("/LNAO")
    public Result LikeNameAndOffice(@RequestBody Doctor d){
        List<Doctor> doctorList = doctorService.LikeNameAndOffice(d);
        Integer code = doctorList!=null?Code.GET_DOCTOR_OK:Code.GET_DOCTOR_ERR;
        String msg = doctorList!=null?"Yes":"No";
        if(doctorList==null){
            return new Result(code,msg,doctorList);
        }
        for (Doctor doc: doctorList) {
            Dtt dtt = dttService.selectOne(doc.getId());
            String[] morning = dtt.getMorning().split("/");
            String[] afternoon = dtt.getAfternoon().split("/");
            String[] night = dtt.getNight().split("/");
            doc.setMorning(morning);
            doc.setAfternoon(afternoon);
            doc.setNight(night);
        }
        return new Result(code,msg,doctorList);
    }

    @PostMapping("/LSAI")
    public Result LikeSkillAndInfo(@RequestBody Doctor d){
        List<Doctor> doctorList = doctorService.LikeSkillandInfo(d);
        Integer code = doctorList!=null?Code.GET_DOCTOR_OK:Code.GET_DOCTOR_ERR;
        String msg = doctorList!=null?"Yes":"No";
        if(doctorList==null){
            return new Result(code,msg,doctorList);
        }
        for (Doctor doc: doctorList) {
            Dtt dtt = dttService.selectOne(doc.getId());
            String[] morning = dtt.getMorning().split("/");
            String[] afternoon = dtt.getAfternoon().split("/");
            String[] night = dtt.getNight().split("/");
            doc.setMorning(morning);
            doc.setAfternoon(afternoon);
            doc.setNight(night);
        }
        return new Result(code,msg,doctorList);
    }

    @GetMapping("/2d/{size}")
    public Result select2D(@PathVariable Long size){//size表示一组多少个
        List<Doctor> doctorList = doctorService.getAll();
        Integer code = doctorList!=null?Code.GET_DOCTOR_OK:Code.GET_DOCTOR_ERR;
        String msg = doctorList!=null?"Successfully!":"查询失败";
        if(doctorList==null){
            return new Result(code,msg,null);
        }
        ArrayList<List<Doctor>> resultList = new ArrayList<>();
        Long i=0L;
        List<Doctor> tempList = new ArrayList<>();
        for (Doctor w: doctorList) {
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

    @PostMapping("/LEI")
    public Result LikeExceptId(@RequestBody Doctor d){
        List<Doctor> doctorList = doctorService.LikeExceptId(d);
        Integer code = doctorList!=null?Code.GET_DOCTOR_OK:Code.GET_DOCTOR_ERR;
        String msg = doctorList!=null?"Yes":"No";
        if(doctorList==null){
            return new Result(code,msg,doctorList);
        }
        for (Doctor doc: doctorList) {
            Dtt dtt = dttService.selectOne(doc.getId());
            String[] morning = dtt.getMorning().split("/");
            String[] afternoon = dtt.getAfternoon().split("/");
            String[] night = dtt.getNight().split("/");
            doc.setMorning(morning);
            doc.setAfternoon(afternoon);
            doc.setNight(night);
        }
        return new Result(code,msg,doctorList);
    }
}
