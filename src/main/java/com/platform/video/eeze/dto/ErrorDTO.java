package com.platform.video.eeze.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorDTO {
    private String generalMessage;
    private Map<String, String> errorDescription;
}
