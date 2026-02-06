package com.movieFlix.service;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.Movie;
import com.movieFlix.entity.Streaming;
import com.movieFlix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final categoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public Optional<Movie> findById(Long id){
        return repository.findById(id);
    }

    public List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        for (Category category : categories) {
            categoryService.getCategoryById(category.getId()).ifPresent(categoriesFound::add);
        }
        return categoriesFound;
    }

    public List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        for (Streaming streaming : streamings) {
            streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add);
        }
        return streamingsFound;
    }

    public Optional<Movie> update(Long MovieId, Movie updatedMovie){
       Optional<Movie> optMovie = repository.findById(MovieId);
       if (optMovie.isPresent()){

           List<Category> categories = this.findCategories(updatedMovie.getCategories());
           List<Streaming> streamings = this.findStreamings(updatedMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setRating(updatedMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            repository.save(movie);

            return Optional.of(movie);
       }
       return Optional.empty();

    }

    public List<Movie> findByCategorie(Long categoryId){
        return  repository.findByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public void delete (Long movieId){
        repository.deleteById(movieId);
    }
}
