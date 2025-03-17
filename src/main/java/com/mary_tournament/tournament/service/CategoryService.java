package com.mary_tournament.tournament.service;

import com.mary_tournament.tournament.model.CategoryGame;
import com.mary_tournament.tournament.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryGame> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryGame getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
    }

    public CategoryGame createCategory(CategoryGame category) {
        return categoryRepository.save(category);
    }
}
