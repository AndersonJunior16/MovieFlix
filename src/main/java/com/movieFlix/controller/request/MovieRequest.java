package com.movieFlix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MovieRequest(String title,
                           String description,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate releaseDate,
                           Double rating,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt,
                           List<Long> categories,
                           List<Long> streamings) {
}
