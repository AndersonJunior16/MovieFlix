package com.movieFlix.mapper;

import com.movieFlix.controller.request.StreamingRequest;
import com.movieFlix.controller.response.StreamingResponse;
import com.movieFlix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse (Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
