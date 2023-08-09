package com.pwojcieszak.frontService.service;

import com.pwojcieszak.frontService.dto.SkillsResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class FrontService {
    private final WebClient.Builder webClientBuilder;

    public FrontService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    public List<SkillsResponse> getAllSkills() {
        Mono<List<SkillsResponse>> skillsMono = webClientBuilder.build().get()
                .uri("http://SKILLS-SERVICE/api/skills/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<SkillsResponse>>() {})
                .onErrorResume(throwable -> {
                    return Mono.just(Collections.emptyList());
                });

        return skillsMono.blockOptional()
                .orElse(Collections.emptyList());
    }
}
