package com.movieFlix.controller;

import com.movieFlix.entity.Category;
import com.movieFlix.mapper.CategoryMapper;
import com.movieFlix.service.categoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.movieFlix.controller.request.*;
import com.movieFlix.controller.response.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final categoryService categoryService;

    @GetMapping
    public List<CategoryRes> getAllCategories(){
        List<Category> categories = categoryService.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    @PostMapping
    public CategoryRes saveCategory(@RequestBody CategoryReq request){
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.save(newCategory);
        return CategoryMapper.toCategoryResponse(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryRes getCategoryById(@PathVariable Long id){
        Optional<Category> optCategory = categoryService.getCategoryById(id);

        if (optCategory.isPresent()){
            return CategoryMapper.toCategoryResponse(optCategory.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategotyById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }

}
