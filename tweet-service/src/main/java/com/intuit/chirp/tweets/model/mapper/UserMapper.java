package com.intuit.chirp.tweets.model.mapper;

import com.intuit.chirp.tweets.model.domain.User;
import com.intuit.chirp.tweets.model.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
