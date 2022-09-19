package com.on99ft.Controller;

import com.on99ft.domain.Article;
import com.on99ft.domain.Billboard;
import com.on99ft.service.BillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/billboard")
public class BillboardController {
    @Autowired
    private BillboardService billboardService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Billboard billboard){
        boolean pd = billboardService.insert(billboard);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Billboard billboard){
        boolean pd = billboardService.update(billboard);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @DeleteMapping
    public Result delete(@RequestBody Billboard billboard){
        boolean pd = billboardService.delete(billboard);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd?Code.SDELETE_OK:Code.SDELETE_ERR,pd);
    }

    @GetMapping("/{id}")
    public Result selectId(@PathVariable Long id){
        Billboard billboard = billboardService.selectById(id);
        Integer code = billboard!=null?Code.GET_OK:Code.GET_ERR;
        String msg = billboard!=null?"Successfully!":"查询失败";
        return new Result(code,msg,billboard);
    }

    @GetMapping
    public Result selectAll(){
        List<Billboard> billboardList = billboardService.selectAll();
        Integer code = billboardList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = billboardList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,billboardList);
    }

    @GetMapping("/gs")
    public Result selectBF(){
        List<Billboard> billboardList = billboardService.selectAll();
        Integer code = billboardList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = billboardList!=null?"Successfully!":"查询失败";
        if(billboardList==null){
            return new Result(code,msg,null);
        }
        for (Billboard w:
                billboardList) {
            if(w.getText()==null||("".equals(w.getText()))){
                w.setText("无");
            }
            if(w.getText().length()>=15){
                w.setText(w.getText().substring(0,15));
            }
        }
        return new Result(code,msg,billboardList);
    }

    @GetMapping("/gs/{cur}/{size}")
    public Result selectBF3(@PathVariable Long cur,@PathVariable Long size){
        List<Billboard> billboardList = billboardService.selectWithLimit(cur,size);
        Integer code = billboardList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = billboardList!=null?"Successfully!":"查询失败";
        if(billboardList==null){
            return new Result(code,msg,null);
        }
        for (Billboard w:
                billboardList) {
            if(w.getText()==null||("".equals(w.getText()))){
                w.setText("无");
            }
            if(w.getText().length()>=15){
                w.setText(w.getText().substring(0,15));
            }
        }
        return new Result(code,msg,billboardList);
    }

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",billboardService.countBillboard());
    }

    @GetMapping("/2d/{size}")
    public Result select2D(@PathVariable Long size){//size表示一组多少个
        List<Billboard> billboardList = billboardService.selectAll();
        Integer code = billboardList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = billboardList!=null?"Successfully!":"查询失败";
        if(billboardList==null){
            return new Result(code,msg,null);
        }
        ArrayList<List<Billboard>> resultList = new ArrayList<>();
        Long i=0L;
        List<Billboard> tempList = new ArrayList<>();
        for (Billboard w: billboardList) {
            if(i.equals(size)){
                i=0L;
                resultList.add(tempList);
                tempList=new ArrayList<>();
            }
            if(w.getText()==null||("".equals(w.getText()))){
                w.setText("无");
            }
            if(w.getText().length()>=15){
                w.setText(w.getText().substring(0,15));
            }
            i+=1L;
            tempList.add(w);
        }
        if(!tempList.isEmpty()){
            resultList.add(tempList);
        }
        return new Result(code,msg,resultList);
    }
}
