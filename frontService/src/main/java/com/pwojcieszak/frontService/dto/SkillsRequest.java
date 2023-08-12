package com.pwojcieszak.frontService.dto;

public record SkillsRequest(String name, String category, String description) {
    public SkillsRequest() {
        this("","","");
    }
}
