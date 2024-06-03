package com.platform.video.eeze.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTOCreateRequest {

    private static final String MIN_YEAR_ERROR_MESSAGE = "The release year can not be less than 1888. The first film was released in 1888";

    @NotNull
    private String title;
    @NotNull
    private String synopsis;
    @NotNull
    private String director;
    @NotNull
    @Min(value = 1888, message = MIN_YEAR_ERROR_MESSAGE)
    private int releaseYear;
    @NotNull
    private int duration;
    @NotNull
    private String genre;
    private String mainActor;
    @NotNull
    private String content;
    private List<Long> actorIds;

}
