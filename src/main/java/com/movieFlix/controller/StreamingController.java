package com.movieFlix.controller;

import com.movieFlix.controller.request.StreamingRequest;
import com.movieFlix.controller.response.StreamingResponse;
import com.movieFlix.entity.Streaming;
import com.movieFlix.mapper.StreamingMapper;
import com.movieFlix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    public ResponseEntity<List<StreamingResponse>> getAllStreaming(){
        return ResponseEntity.ok(streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> saveCategory(@RequestBody StreamingRequest streamingRequest){
        Streaming newStreaming = StreamingMapper.toStreaming(streamingRequest);
        Streaming savedStreaming = streamingService.save(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/id")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        streamingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
