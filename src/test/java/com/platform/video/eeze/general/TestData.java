package com.platform.video.eeze.general;

import com.platform.video.eeze.dto.ActorDTO;
import com.platform.video.eeze.dto.VideoDTOResponse;
import com.platform.video.eeze.dto.VideoDTOSummary;
import com.platform.video.eeze.dto.VideoDTOUpdateRequest;
import com.platform.video.eeze.entity.Actor;
import com.platform.video.eeze.entity.Video;

import java.util.List;

public class TestData {

    public static Video getTestVideo(){
        return new Video(
            1L,
            "Test-Title",
            "Test-Synopsis",
            "Test-Director",
            2005,
            125,
            "Test-Genre",
            "Test-Main-Actor",
            null,
            false,
            "Test-Content",
            List.of(getTestActor())
        );
    }

    public static VideoDTOResponse getTestVideoDTOResponse() {
        return new VideoDTOResponse(
            1L,
            "Test-Title",
            "Test-Synopsis",
            "Test-Director",
            2005,
            125,
            "Test-Genre",
            "Test_Main_Actor",
            null,
            "Test-Content",
            List.of(getTestActorDTO())
        );
    }

    public static VideoDTOUpdateRequest getTestVideoDTOUpdateRequest() {
        return new VideoDTOUpdateRequest(
            "Test-Title",
            "Test-Synopsis",
            "Test-Director",
            2005,
            125,
            "Test-Genre",
            "Test_Main_Actor",
            "Test-Content",
            List.of(1L)
        );
    }

    public static VideoDTOSummary getTestVideoDTOSummary() {
        return new VideoDTOSummary(
            "Test-Title",
            "Test-Director",
            "Test-Main-Actor",
            "Test-Genre",
            125
        );
    }

    public static Actor getTestActor() {
        return new Actor(
            1L,
            "Test_Actor_Name",
            null
        );
    }

    public static ActorDTO getTestActorDTO() {
        return new ActorDTO("Test-Actor_Name");
    }
}
