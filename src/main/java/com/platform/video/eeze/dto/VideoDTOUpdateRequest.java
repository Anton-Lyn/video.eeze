package com.platform.video.eeze.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTOUpdateRequest {

    private String title;
    private String synopsis;
    private String director;
    private int releaseYear;
    private int duration;
    private String genre;
    private String mainActor;
    private String content;
    private List<Long> actorIds;
}
