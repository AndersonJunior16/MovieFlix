package com.movieFlix.mapper;

import com.movieFlix.controller.request.CategoryReq;
import com.movieFlix.controller.response.CategoryRes;
import com.movieFlix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory (CategoryReq categoryReq){
        return Category
                .builder()
                .name(categoryReq.name())
                .build();
    }

    public static CategoryRes toCategoryResponse (Category category){
        return CategoryRes
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
