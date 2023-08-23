package com.pwojcieszak.notificationService.listener;

import com.pwojcieszak.notificationService.event.SkillCreatedEvent;
import com.pwojcieszak.notificationService.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatingSkillListener {
    private final EmailService emailService;
    @KafkaListener(topics = "creatingSkillTopic")
    public void handleNotification(SkillCreatedEvent event){
        log.info("Created skill with ID of {}", event.id());

        emailService.sendEmailToAdmin(event);
    }
}
