package com.platform.video.eeze.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EngagementDTOResponse {

    private Long videoId;
    private Long impressions;
    private Long views;

}
