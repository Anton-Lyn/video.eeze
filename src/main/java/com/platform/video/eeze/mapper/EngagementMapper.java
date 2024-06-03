package com.platform.video.eeze.mapper;

import com.platform.video.eeze.dto.EngagementDTOResponse;
import com.platform.video.eeze.entity.Engagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EngagementMapper {

    @Mapping(target = "videoId", source = "video.videoId")
    EngagementDTOResponse engagementToEngagementDTOResponse(Engagement engagement);

}
