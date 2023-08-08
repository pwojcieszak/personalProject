package com.pwojcieszak.repository;

import com.pwojcieszak.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository <Skill, Long> {
    Integer deleteByName(String name);
}
