package com.dev.socialmedia.controllers;

import com.dev.socialmedia.services.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public void createCategories(@RequestBody String name) {
        categoryService.createCategory(name);
    }
}
