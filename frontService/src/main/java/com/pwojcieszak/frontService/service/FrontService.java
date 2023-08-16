package com.pwojcieszak.frontService.service;

import com.pwojcieszak.frontService.dto.SkillsRequest;
import com.pwojcieszak.frontService.dto.SkillsResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
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
                .onErrorResume(throwable -> Mono.just(Collections.emptyList()));

        return skillsMono.blockOptional()
                .orElse(Collections.emptyList());
    }

    public Boolean deleteSkill(String name) {
        Mono<Boolean> skillsMono = webClientBuilder.build().delete()
                .uri("http://SKILLS-SERVICE/api/skills/delete/{name}", name)
                .retrieve()
                .toBodilessEntity()
                .map(responseEntity -> responseEntity.getStatusCode() == HttpStatus.OK)
                .defaultIfEmpty(false);

        return skillsMono.blockOptional()
                        .orElse(false);
    }

    public boolean createSkill(SkillsRequest skillsRequest) {
        Mono<Boolean> skillsMono = webClientBuilder.build().post()
                .uri("http://SKILLS-SERVICE/api/skills/new")
                .body(BodyInserters.fromValue(skillsRequest))
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(throwable -> Mono.just(false));
        return skillsMono.blockOptional()
                .orElse(false);
    }
}
