package com.pwojcieszak.frontService.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class frontServiceController {
    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/aboutMe")
    public String getAboutMe(){
        return "aboutMe";
    }
}
