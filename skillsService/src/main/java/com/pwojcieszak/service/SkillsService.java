package com.pwojcieszak.service;

import com.pwojcieszak.dto.SkillsResponse;
import com.pwojcieszak.model.Skill;
import com.pwojcieszak.repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillsService {
    private final SkillsRepository skillsRepository;
    public List<SkillsResponse> findAllSkills() {
        return skillsRepository.findAll().stream()
                .map(skill ->
                        new SkillsResponse(
                                skill.getName(),
                                skill.getCategory(),
                                skill.getDescription()
                        )
                )
                .toList();
    }

    public Optional<Skill> createNewSkill(Skill skill) {
        Skill createdSkill =  skillsRepository.save(skill);

        return Optional.of(createdSkill);
    }

    public Integer deleteSkillByName(String name) {
        return skillsRepository.deleteByName(name);
    }


}
