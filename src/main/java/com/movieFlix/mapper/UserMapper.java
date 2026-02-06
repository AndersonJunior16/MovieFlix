package com.movieFlix.mapper;

import com.movieFlix.controller.request.UserRequest;
import com.movieFlix.controller.response.UserResponse;
import com.movieFlix.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static User toUser(UserRequest request){
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
