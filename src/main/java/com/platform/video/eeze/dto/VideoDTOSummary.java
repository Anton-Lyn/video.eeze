package com.platform.video.eeze.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTOSummary {
    private String title;
    private String director;
    private String mainActor;
    private String genre;
    private int duration;
}
