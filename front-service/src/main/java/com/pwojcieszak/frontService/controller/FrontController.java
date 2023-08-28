package com.pwojcieszak.frontService.controller;

import com.pwojcieszak.frontService.dto.SkillsRequest;
import com.pwojcieszak.frontService.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/front")
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

    @GetMapping("/skills/delete/{name}")
    public String deleteSkill(@PathVariable String name, RedirectAttributes redirectAttributes){
        boolean success = frontService.deleteSkill(name);
        if (success) {
            redirectAttributes.addFlashAttribute("result", "Successfully deleted selected skill");
        } else {
            redirectAttributes.addFlashAttribute("result", "Something went wrong");
        }
        return "redirect:http://localhost:8080/front/aboutMe";
    }

    @GetMapping("/skills/new")
    public String createSkillForm(Model model) {
        SkillsRequest skillRequest = new SkillsRequest();
        model.addAttribute("skill", skillRequest);
        return "createSkill";
    }

    @PostMapping("/aboutMe")
    public String saveStudent(@ModelAttribute("skill") SkillsRequest skillsRequest, RedirectAttributes redirectAttributes) {
        boolean success = frontService.createSkill(skillsRequest);
        if (success) {
            redirectAttributes.addFlashAttribute("result", "Successfully added skill");
        } else {
            redirectAttributes.addFlashAttribute("result", "Something went wrong");
        }
        return "redirect:http://localhost:8080/front/aboutMe";
    }

}
