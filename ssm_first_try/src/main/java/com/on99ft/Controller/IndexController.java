package com.on99ft.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class IndexController {
    @RequestMapping("/index")
    public String toIndex(){
        return "redirect:/FromOn99MuBan/OriginMenu/on99/FirstSee.html";
    }
}
