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
    private final WebClient webClient;

    public FrontService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public List<SkillsResponse> getAllSkills() {
        Mono<List<SkillsResponse>> skillsMono = webClient.get()
                .uri("/skills/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<SkillsResponse>>() {})
                .onErrorResume(throwable -> {
                    return Mono.just(Collections.emptyList());
                });

        return skillsMono.blockOptional()
                .orElse(Collections.emptyList());
    }
}
