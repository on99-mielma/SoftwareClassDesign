package com.on99ft.Controller;

import com.on99ft.domain.User;
import com.on99ft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Result save(@RequestBody User user){
        boolean pd = userService.save(user);
        return new Result(pd?Code.SAVE_OK:Code.SAVE_ERR,pd);
    }

    @PutMapping("/change")
    public Result update(@RequestBody User user){
        boolean pd = userService.update(user);
        return new Result(pd?Code.UPDATE_OK:Code.UPDATE_ERR,pd);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean pd = userService.delete(id);
        return new Result(pd?Code.DELETE_OK:Code.DELETE_ERR,pd);
    }

    @GetMapping("/{id}")
    public Result selectId(@PathVariable Long id){
        User user = userService.selectId(id);
        Integer code = user!=null?Code.GET_OK:Code.GET_ERR;
        String msg = user!=null?"Successfully!":"查询失败";
        return new Result(code,msg,user);
    }

    @GetMapping
    public Result selectAll(){
        List<User> userList = userService.selectAll();
        Integer code = userList!=null?Code.GET_OK:Code.GET_ERR;
        String msg = userList!=null?"Successfully!":"查询失败";
        return new Result(code,msg,userList);
    }

    @PostMapping("/login")
    public Result trylogin(HttpSession session,@RequestBody User user){
        User w = userService.dologin(user);
        Integer code = w!=null?Code.GET_OK:Code.GET_ERR;
        String msg = w!=null?"Successfully!":"404";
        /*System.out.println("w = " + w);*/
        if(w!=null){
            session.setAttribute("theId",w.getuId());
            session.setAttribute("theName",w.getuName());
            session.setAttribute("thePw",w.getuPw());
        }
        else{
            session.setAttribute("theId",404L);
        }
        return new Result(code,msg,w);
    }

    @GetMapping("/checkStatus")
    public Result checkStatus(HttpSession session){
        Long theId = (Long) session.getAttribute("theId");
        if(theId==null||theId==404L||theId==0L){
            return new Result(Code.GET_ERR,"404",theId);
        }
        User loginUser = userService.selectId(theId);
        return new Result(Code.GET_OK,"Successfully!",loginUser);
    }

    @GetMapping("/count")
    public Result countUsers(){
        return new Result(Code.GET_OK,"^^",userService.countUser());
    }

}
