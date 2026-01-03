package com.movieFlix.controller.response;

import lombok.Builder;

@Builder
public record CategoryRes(Long id, String name) {
}
