package com.platform.video.eeze.controllers;

import com.platform.video.eeze.dto.VideoDTOCreateRequest;
import com.platform.video.eeze.dto.VideoDTOResponse;
import com.platform.video.eeze.dto.VideoDTOSummary;
import com.platform.video.eeze.dto.VideoDTOUpdateRequest;
import com.platform.video.eeze.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<VideoDTOResponse> publishVideo(@Valid @RequestBody VideoDTOCreateRequest videoDTOCreateRequest) {
        VideoDTOResponse resp = videoService.publishVideo(videoDTOCreateRequest);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<VideoDTOResponse> addMetaData(@PathVariable Long videoId,
                                                        @RequestBody VideoDTOUpdateRequest videoDTOUpdateRequest) {
        return new ResponseEntity<>(videoService.editMetadata(videoId, videoDTOUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long videoId) {
        videoService.deleteVideo(videoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoDTOResponse> loadVideo(@PathVariable Long videoId) {
        return new ResponseEntity<>(videoService.loadVideo(videoId), HttpStatus.OK);
    }

    @GetMapping("/{videoId}/play")
    public ResponseEntity<String> playVideo(@PathVariable Long videoId) {
        return new ResponseEntity<>(videoService.playVideo(videoId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VideoDTOSummary>> getAllVideos() {
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoDTOSummary>> getVideoByDirector(@RequestParam(required = false) String director) {
        return new ResponseEntity<>(videoService.getVideoByDirector(director), HttpStatus.OK);
    }
}
