package com.movieFlix.controller;

import com.movieFlix.controller.request.MovieRequest;
import com.movieFlix.controller.response.MovieResponse;
import com.movieFlix.entity.Movie;
import com.movieFlix.mapper.MovieMapper;
import com.movieFlix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save (@RequestBody MovieRequest request){
        Movie savedMovie = service.save(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies (){
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieId (@PathVariable Long id){
        return   service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest request){
        return service.update(id, MovieMapper.toMovie(request ))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findBycategories (@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategorie(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        Optional<Movie> optMovie = service.findById(id);

        if (optMovie.isPresent()){

            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
