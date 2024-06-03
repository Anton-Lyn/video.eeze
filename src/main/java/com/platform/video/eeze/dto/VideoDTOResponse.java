package com.platform.video.eeze.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTOResponse {

    private Long videoId;
    private String title;
    private String synopsis;
    private String director;
    private int releaseYear;
    private int duration;
    private String genre;
    private String mainActor;
    private LocalDate uploadDate;
    private String content;
    private List<ActorDTO> actors;
}
