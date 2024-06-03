package com.platform.video.eeze.controllers;

import com.platform.video.eeze.dto.EngagementDTOResponse;
import com.platform.video.eeze.service.EngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video-engagements")
public class EngagementController {

    @Autowired
    private EngagementService engagementService;

    @GetMapping("/{videoId}")
    public ResponseEntity<EngagementDTOResponse> loadVideo(@PathVariable Long videoId) {
        return new ResponseEntity<>(engagementService.getEngagementByVideoId(videoId), HttpStatus.OK);
    }
}
