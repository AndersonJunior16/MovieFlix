package com.movieFlix.repository;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Long id(Long id);

    List<Movie> findByCategories(List<Category> categories);
}
