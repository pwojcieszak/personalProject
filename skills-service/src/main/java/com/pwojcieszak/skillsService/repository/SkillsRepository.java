package com.pwojcieszak.skillsService.repository;

import com.pwojcieszak.skillsService.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository <Skill, Long> {
    void deleteByName(String name);
}
