package com.pwojcieszak.frontService.dto;

import lombok.Data;

@Data
public class SkillsRequest {
    private String name;
    private String category;
    private String description;
}
