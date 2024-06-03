package com.platform.video.eeze.mapper;

import com.platform.video.eeze.dto.VideoDTOCreateRequest;
import com.platform.video.eeze.dto.VideoDTOResponse;
import com.platform.video.eeze.dto.VideoDTOSummary;
import com.platform.video.eeze.dto.VideoDTOUpdateRequest;
import com.platform.video.eeze.entity.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ActorMapper.class)
public interface VideoMapper {

    @Mapping(target = "actors", source = "actors")
    VideoDTOResponse videoToVideoDTOResponse(Video video);
    @Mapping(target = "videoId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "uploadDate", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Video videoCreateRequestToVideo(VideoDTOCreateRequest videoDTOCreateRequest);

    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "uploadDate", ignore = true)
    @Mapping(target = "videoId", ignore = true)
    @Mapping(target = "actors", ignore = true)
    Video updateVideo(VideoDTOUpdateRequest updateRequest, @MappingTarget Video existingVideo);

    VideoDTOSummary videoToVideoDTOSummary(Video video);
}
