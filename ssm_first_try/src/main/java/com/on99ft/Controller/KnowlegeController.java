package com.on99ft.Controller;

import com.on99ft.domain.Knowledge;
import com.on99ft.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Integer code = k!=null?Code.GET_OK:Code.GET_ERR;
        String msg = k!=null?"Yes":"No";
        return new Result(code,msg,k);
    }

    @GetMapping
    public Result getAll(){
        List<Knowledge> knowledgeList = knowledgeService.getAll();
        Integer code = knowledgeList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = knowledgeList!=null?"Yes":"No";
        return new Result(code,msg,knowledgeList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        Integer pd = knowledgeService.delete(id);
        return new Result(pd>0?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }
}
