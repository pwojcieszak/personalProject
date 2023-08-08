package com.pwojcieszak.frontService.Controller;

import com.pwojcieszak.frontService.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    private final FrontService frontService;
    @Autowired
    public FrontController(FrontService frontService) {
        this.frontService = frontService;
    }

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/aboutMe")
    public String getAboutMe(Model model){
        model.addAttribute("skills", frontService.getAllSkills());
        return "aboutMe";
    }
}
