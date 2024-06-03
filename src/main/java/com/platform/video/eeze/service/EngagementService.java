package com.platform.video.eeze.service;

import com.platform.video.eeze.dto.EngagementDTOResponse;
import com.platform.video.eeze.exceptions.VideoNotFoundException;
import com.platform.video.eeze.mapper.EngagementMapper;
import com.platform.video.eeze.repository.EngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngagementService {

    @Autowired
    private EngagementRepository engagementRepository;
    @Autowired
    private EngagementMapper engagementMapper;

    public EngagementDTOResponse getEngagementByVideoId(Long videoId) {
        return engagementMapper.engagementToEngagementDTOResponse(engagementRepository.findById(videoId)
            .orElseThrow(() -> new VideoNotFoundException("Video not found")));
    }

}
