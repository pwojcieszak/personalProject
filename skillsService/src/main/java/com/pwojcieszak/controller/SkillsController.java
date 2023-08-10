package com.pwojcieszak.controller;

import com.pwojcieszak.dto.SkillsResponse;
import com.pwojcieszak.model.Skill;
import com.pwojcieszak.service.SkillsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillsController {
    private final SkillsService skillsService;

    @GetMapping("/all")
    public ResponseEntity<List<SkillsResponse>> getAllSkils(){
        List<SkillsResponse> skills = skillsService.findAllSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Skill> createNewSkill(@RequestBody Skill skill) {
        Optional<Skill> answer = skillsService.createNewSkill(skill);

        if(answer.isPresent())
            return new ResponseEntity<>(answer.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{name}")
    @Transactional
    public ResponseEntity<Integer> deleteSkillByName(@PathVariable String name) {
        Integer numberOfRowsDeleted =  skillsService.deleteSkillByName(name);
        return ResponseEntity.ok().body(numberOfRowsDeleted);
    }
}
