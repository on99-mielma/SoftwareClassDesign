package com.on99ft.Controller;

import com.on99ft.domain.Article;
import com.on99ft.domain.Knowledge;
import com.on99ft.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowlegeController {
    @Autowired
    private KnowledgeService knowledgeService;

    @PostMapping
    public Result insert(@RequestBody Knowledge k){
        Integer pd = knowledgeService.insert(k);
        return new Result(pd>0?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Knowledge k){
        Integer pd = knowledgeService.update(k);
        return new Result(pd>0?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @GetMapping("/{id}")
    public Result getByid(@PathVariable Long id){
        Knowledge k = knowledgeService.getById(id);
        Integer code = k!=null?Code.GET_KNOWLEDGE_OK:Code.GET_KNOWLEDGE_ERR;
        String msg = k!=null?"Yes":"No";
        return new Result(code,msg,k);
    }

    @GetMapping
    public Result getAll(){
        List<Knowledge> knowledgeList = knowledgeService.getAll();
        Integer code = knowledgeList!=null?Code.GET_KNOWLEDGE_OK:Code.GET_KNOWLEDGE_ERR;
        String msg = knowledgeList!=null?"Yes":"No";
        return new Result(code,msg,knowledgeList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        Integer pd = knowledgeService.delete(id);
        return new Result(pd>0?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }

    @GetMapping("/count")
    public Result countKnowledge(){
        return new Result(Code.GET_KNOWLEDGE_OK,"^^",knowledgeService.countKnowledge());
    }

    @GetMapping("/gs")
    public Result selectBF(){
        List<Knowledge> knowledgeList = knowledgeService.getAll();
        Integer code = knowledgeList!=null?Code.GET_KNOWLEDGE_OK:Code.GET_KNOWLEDGE_ERR;
        String msg = knowledgeList!=null?"Successfully!":"查询失败";
        if(knowledgeList==null){
            return new Result(code,msg,null);
        }
        for (Knowledge k: knowledgeList) {
            if(k.getText()==null||("".equals(k.getText()))){
                k.setText("无");
            }
            if(k.getText().length()>=15){
                k.setText(k.getText().substring(0,15));
            }
        }
        return new Result(code,msg,knowledgeList);
    }

    @GetMapping("/gs/{cur}/{size}")
    public Result selectBFL(@PathVariable Long cur,@PathVariable Long size){
        List<Knowledge> knowledgeList = knowledgeService.selectWithLimit(cur,size);
        Integer code = knowledgeList!=null?Code.GET_KNOWLEDGE_OK:Code.GET_KNOWLEDGE_ERR;
        String msg = knowledgeList!=null?"Successfully!":"查询失败";
        if(knowledgeList==null){
            return new Result(code,msg,null);
        }
        for (Knowledge k: knowledgeList) {
            if(k.getText()==null||("".equals(k.getText()))){
                k.setText("无");
            }
            if(k.getText().length()>=15){
                k.setText(k.getText().substring(0,15));
            }
        }
        return new Result(code,msg,knowledgeList);
    }


    @GetMapping("/2d/{size}")
    public Result select2D(@PathVariable Long size){//size表示一组多少个
        List<Knowledge> knowledgeList = knowledgeService.getAll();
        Integer code = knowledgeList!=null?Code.GET_KNOWLEDGE_OK:Code.GET_KNOWLEDGE_ERR;
        String msg = knowledgeList!=null?"Successfully!":"查询失败";
        if(knowledgeList==null){
            return new Result(code,msg,null);
        }
        ArrayList<List<Knowledge>> resultList = new ArrayList<>();
        Long i=0L;
        List<Knowledge> tempList = new ArrayList<>();
        for (Knowledge w: knowledgeList) {
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
