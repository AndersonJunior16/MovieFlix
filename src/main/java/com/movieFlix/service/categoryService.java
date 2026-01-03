package com.movieFlix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.movieFlix.repository.CategoryRepository;
import com.movieFlix.entity.Category;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class categoryService {

    private final CategoryRepository respository;

    public List<Category> findAll(){
        return respository.findAll();
    }

    public Category save(Category category){
        return respository.save(category);
    }

    public Optional<Category> getCategoryById (Long id){
        return respository.findById(id);
    }

    public void deleteCategoryById(Long id){
        respository.deleteById(id);
    }

}
