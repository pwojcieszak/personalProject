package com.pwojcieszak.skillsService.service;

import com.pwojcieszak.skillsService.dto.SkillsResponse;
import com.pwojcieszak.skillsService.event.SkillCreatedEvent;
import com.pwojcieszak.skillsService.model.Skill;
import com.pwojcieszak.skillsService.repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final KafkaTemplate<String, SkillCreatedEvent> kafkaTemplate;
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

        kafkaTemplate.send("creatingSkillTopic", new SkillCreatedEvent(skill.getId()));

        return Optional.of(createdSkill);
    }

    public void deleteSkillByName(String name) {
        skillsRepository.deleteByName(name);
    }


}
