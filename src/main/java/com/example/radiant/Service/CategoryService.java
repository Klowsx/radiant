package com.example.radiant.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Category;
import com.example.radiant.Repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deteleCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        categoryRepository.delete(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category curretnCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        curretnCategory.setName(category.getName());
        curretnCategory.setDescription(category.getDescription());
        return categoryRepository.save(curretnCategory);
    }
}
