package com.on99ft.Controller;

import com.on99ft.domain.Article;
import com.on99ft.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Article article){
        boolean pd = articleService.insert(article);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping
    public Result update(@RequestBody Article article){
        boolean pd = articleService.update(article);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @DeleteMapping
    public Result delete(@RequestBody Article article){
        boolean pd = articleService.delete(article);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd?Code.SDELETE_OK:Code.SDELETE_ERR,pd);
    }

    @GetMapping("/{id}")
    public Result selectId(@PathVariable Long id){
        Article article = articleService.selectId(id);
        Integer code = article!=null?Code.GET_OK:Code.GET_ERR;
        String msg = article!=null?"Successfully!":"查询失败";
        return new Result(code,msg,article);
    }

    @GetMapping
    public Result selectAll(){
        List<Article> articleList = articleService.selectAll();
        Integer code = articleList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = articleList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,articleList);
    }

    @GetMapping("/gs")
    public Result selectBF(){
        List<Article> articleList = articleService.selectAll();
        Integer code = articleList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = articleList!=null?"Successfully!":"查询失败";
        if(articleList==null){
            return new Result(code,msg,null);
        }
        for (Article w:
             articleList) {
            if(w.getText()==null||("".equals(w.getText()))){
                w.setText("无");
            }
            if(w.getText().length()>=15){
                w.setText(w.getText().substring(0,15));
            }
        }
        return new Result(code,msg,articleList);
    }

    @GetMapping("/gs3")
    public Result selectBF3(){
        List<Article> articleList = articleService.selectAll();
        Integer code = articleList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = articleList!=null?"Successfully!":"查询失败";
        if(articleList==null){
            return new Result(code,msg,null);
        }
        int alen = Math.min(articleList.size(),3);
        List<Article> articleList1 = articleList.subList(0,alen);
        for (Article w:
                articleList1) {
            if(w.getText()==null||("".equals(w.getText()))){
                w.setText("无");
            }
            if(w.getText().length()>=15){
                w.setText(w.getText().substring(0,15));
            }
        }
        return new Result(code,msg,articleList1);
    }

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",articleService.countArticle());
    }
}
