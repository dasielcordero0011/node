package com.santander.processinstancevisualizer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {

    @JsonProperty("code")
    private int code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("level")
    private String level;

    @JsonProperty("message")
    private String message;
}

