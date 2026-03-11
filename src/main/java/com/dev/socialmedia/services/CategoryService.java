package com.dev.socialmedia.services;

import com.dev.socialmedia.models.Category;
import com.dev.socialmedia.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category createCategory(String request) {
        Category category = new Category();
        category.setName(request);
        return categoryRepository.save(category);
    }
}
