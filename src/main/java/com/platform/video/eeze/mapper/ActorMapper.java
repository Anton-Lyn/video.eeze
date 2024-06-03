package com.platform.video.eeze.mapper;

import com.platform.video.eeze.dto.ActorDTO;
import com.platform.video.eeze.entity.Actor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorDTO actorToActorDTO(Actor actor);
}
