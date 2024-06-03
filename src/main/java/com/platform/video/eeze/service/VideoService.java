package com.platform.video.eeze.service;

import com.platform.video.eeze.exceptions.VideoNotFoundException;
import com.platform.video.eeze.dto.VideoDTOCreateRequest;
import com.platform.video.eeze.dto.VideoDTOResponse;
import com.platform.video.eeze.dto.VideoDTOSummary;
import com.platform.video.eeze.dto.VideoDTOUpdateRequest;
import com.platform.video.eeze.entity.Engagement;
import com.platform.video.eeze.entity.Video;
import com.platform.video.eeze.mapper.VideoMapper;
import com.platform.video.eeze.repository.ActorRepository;
import com.platform.video.eeze.repository.EngagementRepository;
import com.platform.video.eeze.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private EngagementRepository engagementRepository;
    @Autowired
    private VideoMapper videoMapper;
    public VideoDTOResponse publishVideo(VideoDTOCreateRequest videoDTOCreateRequest) {
        Video video = videoMapper.videoCreateRequestToVideo(videoDTOCreateRequest);
        video.setActors(actorRepository.findAllById(videoDTOCreateRequest.getActorIds()));
        Video createdVideo = videoRepository.save(video);
        engagementRepository.save(Engagement.builder().video(video).build());
        return videoMapper.videoToVideoDTOResponse(createdVideo);
    }

    public VideoDTOResponse editMetadata(Long videoId, VideoDTOUpdateRequest videoDTOUpdateRequest) {
        Video updatedVideo = videoMapper.updateVideo(videoDTOUpdateRequest,
            videoRepository.findById(videoId).orElseThrow(() -> new VideoNotFoundException("Video not found")));
        updatedVideo.setActors(actorRepository.findAllById(videoDTOUpdateRequest.getActorIds()));
        return videoMapper.videoToVideoDTOResponse(videoRepository.save(updatedVideo));
    }

    public void deleteVideo(Long videoId) {
        videoRepository.deleteById(videoId);
    }

    public VideoDTOResponse loadVideo(Long videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new VideoNotFoundException("Video not found"));
        engagementRepository.incrementImpressionsForVideoByVideoId(video.getVideoId());
        return videoMapper.videoToVideoDTOResponse(video);
    }

    public String playVideo(Long videoId) {
        Video video = videoRepository.getReferenceById(videoId);
        engagementRepository.incrementViewsForVideoByVideoId(videoId);
        return video.getContent();
    }

    @Transactional(readOnly = true)
    public List<VideoDTOSummary> getAllVideos() {
        return videoRepository.findAll().stream()
            .map(video -> videoMapper.videoToVideoDTOSummary(video))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VideoDTOSummary> getVideoByDirector(String director) {
        return videoRepository.findByDirector(director)
            .stream()
            .map(video -> videoMapper.videoToVideoDTOSummary(video))
            .collect(Collectors.toList());
    }
}
