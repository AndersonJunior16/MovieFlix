package com.movieFlix.repository;

import com.movieFlix.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
