package com.pwojcieszak.frontService.service;

import com.pwojcieszak.frontService.dto.SkillsRequest;
import com.pwojcieszak.frontService.dto.SkillsResponse;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FrontService {
    private final Tracer tracer;
    private final WebClient.Builder webClientBuilder;

    public List<SkillsResponse> getAllSkills() {
        Span skillsServiceCall = tracer.nextSpan().name("SkillsServiceCall");
        try(Tracer.SpanInScope spanInScope = tracer.withSpan(skillsServiceCall.start())) {
            Mono<List<SkillsResponse>> skillsMono = webClientBuilder.build().get()
                    .uri("http://SKILLS-SERVICE/api/skills/all")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<SkillsResponse>>() {
                    })
                    .onErrorResume(throwable -> Mono.just(Collections.emptyList()));

            return skillsMono.blockOptional()
                    .orElse(Collections.emptyList());
        }
        finally {
            skillsServiceCall.end();
        }
    }

    public Boolean deleteSkill(String name) {
        Span skillsServiceCall = tracer.nextSpan().name("SkillsServiceCall");
        try(Tracer.SpanInScope spanInScope = tracer.withSpan(skillsServiceCall.start())){
            Mono<Boolean> skillsMono = webClientBuilder.build().delete()
                    .uri("http://SKILLS-SERVICE/api/skills/delete/{name}", name)
                    .retrieve()
                    .toBodilessEntity()
                    .map(responseEntity -> responseEntity.getStatusCode() == HttpStatus.OK)
                    .defaultIfEmpty(false);

            return skillsMono.blockOptional()
                    .orElse(false);
        }
        finally {
            skillsServiceCall.end();
        }
    }

    public boolean createSkill(SkillsRequest skillsRequest) {
        Span skillsServiceCall = tracer.nextSpan().name("SkillsServiceCall");

        try(Tracer.SpanInScope spanInScope = tracer.withSpan(skillsServiceCall.start())){
            Mono<Boolean> skillsMono = webClientBuilder.build().post()
                    .uri("http://SKILLS-SERVICE/api/skills/new")
                    .body(BodyInserters.fromValue(skillsRequest))
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .onErrorResume(throwable -> Mono.just(false));
            return skillsMono.blockOptional()
                    .orElse(false);
        }
        finally {
            skillsServiceCall.end();
        }


    }
}
