package com.platform.video.eeze.service;

import com.platform.video.eeze.dto.VideoDTOCreateRequest;
import com.platform.video.eeze.dto.VideoDTOResponse;
import com.platform.video.eeze.dto.VideoDTOSummary;
import com.platform.video.eeze.dto.VideoDTOUpdateRequest;
import com.platform.video.eeze.entity.Video;
import com.platform.video.eeze.exceptions.VideoNotFoundException;
import com.platform.video.eeze.mapper.VideoMapper;
import com.platform.video.eeze.repository.EngagementRepository;
import com.platform.video.eeze.repository.VideoRepository;
import com.platform.video.eeze.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.platform.video.eeze.general.TestData.getTestActor;
import static com.platform.video.eeze.general.TestData.getTestVideo;
import static com.platform.video.eeze.general.TestData.getTestVideoDTOResponse;
import static com.platform.video.eeze.general.TestData.getTestVideoDTOSummary;
import static com.platform.video.eeze.general.TestData.getTestVideoDTOUpdateRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ActorRepository ActorRepository;

    @Mock
    private EngagementRepository engagementRepository;

    @Mock
    private VideoMapper videoMapper;

    @InjectMocks
    private VideoService videoService;

    @Test
    void publishVideo_ValidInput_ShouldReturnVideoDTOResponse() {
        // Given
        VideoDTOCreateRequest createRequest = new VideoDTOCreateRequest();
        createRequest.setTitle("Test-Title");
        createRequest.setActorIds(List.of(1L));

        Video video = getTestVideo();

        when(videoMapper.videoCreateRequestToVideo(createRequest)).thenReturn(video);
        when(ActorRepository.findAllById(createRequest.getActorIds())).thenReturn(List.of(getTestActor()));
        when(videoRepository.save(any(Video.class))).thenReturn(video);
        when(videoMapper.videoToVideoDTOResponse(video)).thenReturn(getTestVideoDTOResponse());

        // When
        VideoDTOResponse response = videoService.publishVideo(createRequest);

        // Then
        assertNotNull(response);
        assertEquals(video.getTitle(), response.getTitle());
        assertEquals(video.getGenre(), response.getGenre());
        assertEquals(video.getContent(), response.getContent());
    }

    @Test
    void editMetadata_ValidInput_ShouldReturnVideoDTOResponse() {
        // Given
        Long videoId = 1L;
        VideoDTOUpdateRequest updateRequest = getTestVideoDTOUpdateRequest();

        Video video = getTestVideo();

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));
        when(videoMapper.updateVideo(updateRequest, video)).thenReturn(video);
        when(ActorRepository.findAllById(updateRequest.getActorIds())).thenReturn(Arrays.asList(getTestActor(), getTestActor()));
        when(videoRepository.save(video)).thenReturn(video);
        when(videoMapper.videoToVideoDTOResponse(video)).thenReturn(getTestVideoDTOResponse());

        // When
        VideoDTOResponse response = videoService.editMetadata(videoId, updateRequest);

        // Then
        assertNotNull(response);
        assertEquals(video.getTitle(), response.getTitle());
    }

    @Test
    void loadVideo_ValidId_ShouldReturnVideoDTOResponse() {
        // Given
        Long videoId = 1L;
        Video video = getTestVideo();

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));
        when(videoMapper.videoToVideoDTOResponse(video)).thenReturn(getTestVideoDTOResponse());

        // When
        VideoDTOResponse response = videoService.loadVideo(videoId);

        // Then
        assertNotNull(response);
        assertEquals(video.getVideoId(), response.getVideoId());
    }

    @Test
    void loadVideo_InvalidId_ShouldThrowVideoNotFoundException() {
        // Given
        Long videoId = 1L;
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(VideoNotFoundException.class, () -> videoService.loadVideo(videoId));
    }

    @Test
    void playVideo_ValidId_ShouldReturnContent() {
        // Given
        Long videoId = 1L;
        Video video = getTestVideo();

        when(videoRepository.getReferenceById(videoId)).thenReturn(video);

        // When
        String content = videoService.playVideo(videoId);

        // Then
        assertNotNull(content);
        assertEquals(video.getContent(), content);
    }

    @Test
    void getAllVideos_ShouldReturnListOfVideoDTOSummary() {
        // Given
        List<Video> videos = Arrays.asList(getTestVideo(), getTestVideo());
        when(videoRepository.findAll()).thenReturn(videos);
        when(videoMapper.videoToVideoDTOSummary(any(Video.class)))
            .thenReturn(getTestVideoDTOSummary())
            .thenReturn(getTestVideoDTOSummary());

        // When
        List<VideoDTOSummary> summaries = videoService.getAllVideos();

        // Then
        assertNotNull(summaries);
        assertEquals(2, summaries.size());
    }

    @Test
    void getVideoByDirector_ShouldReturnListOfVideoDTOSummary() {
        // Given
        String director = "Test-Director";
        List<Video> videos = Arrays.asList(getTestVideo(), getTestVideo());
        when(videoRepository.findByDirector(director)).thenReturn(videos);
        when(videoMapper.videoToVideoDTOSummary(any(Video.class)))
            .thenReturn(getTestVideoDTOSummary())
            .thenReturn(getTestVideoDTOSummary());

        // When
        List<VideoDTOSummary> summaries = videoService.getVideoByDirector(director);

        // Then
        assertNotNull(summaries);
        assertEquals(2, summaries.size());
    }
}