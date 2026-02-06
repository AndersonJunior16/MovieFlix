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

    private final CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public Optional<Category> getCategoryById (Long id){
        return repository.findById(id);
    }

    public void deleteCategoryById  (Long id){
        repository.deleteById(id);
    }

}
