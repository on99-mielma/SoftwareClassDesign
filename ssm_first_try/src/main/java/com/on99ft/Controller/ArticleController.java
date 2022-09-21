package com.on99ft.Controller;

import com.on99ft.domain.Article;
import com.on99ft.domain.Billboard;
import com.on99ft.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        Integer code = article!=null?Code.GET_ARTICLE_OK:Code.GET_ARTICLE_ERR;
        String msg = article!=null?"Successfully!":"查询失败";
        return new Result(code,msg,article);
    }

    @GetMapping
    public Result selectAll(){
        List<Article> articleList = articleService.selectAll();
        Integer code = articleList!=null?Code.GET_ARTICLE_OK:Code.GET_ARTICLE_ERR;
        String msg = articleList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,articleList);
    }

    @GetMapping("/gs")
    public Result selectBF(){
        List<Article> articleList = articleService.selectAll();
        Integer code = articleList!=null?Code.GET_ARTICLE_OK:Code.GET_ARTICLE_ERR;
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

    @GetMapping("/gs/{cur}/{size}")
    public Result selectBF3(@PathVariable Long cur,@PathVariable Long size){
        List<Article> articleList = articleService.selectWithLimit(cur,size);
        Integer code = articleList!=null?Code.GET_ARTICLE_OK:Code.GET_ARTICLE_ERR;
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

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_ARTICLE_OK,"^^",articleService.countArticle());
    }

    @GetMapping("/2d/{size}")
    public Result select2D(@PathVariable Long size){//size表示一组多少个
        List<Article> articleList = articleService.selectAll();
        Integer code = articleList!=null?Code.GET_ARTICLE_OK:Code.GET_ARTICLE_ERR;
        String msg = articleList!=null?"Successfully!":"查询失败";
        if(articleList==null){
            return new Result(code,msg,null);
        }
        ArrayList<List<Article>> resultList = new ArrayList<>();
        Long i=0L;
        List<Article> tempList = new ArrayList<>();
        for (Article w: articleList) {
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

    @PostMapping("/LTOT")
    public Result LikeTitleOrText(@RequestBody Article a){
        List<Article> articleList = articleService.LikeTitleOrText(a);
        Integer code = articleList!=null?Code.GET_ARTICLE_OK:Code.GET_ARTICLE_ERR;
        String msg = articleList!=null?"Yes":"无结果";
        if(articleList==null){
            return  new Result(code,msg,articleList);
        }
        return new Result(code,msg,articleList);
    }
}
