package com.mary_tournament.tournament.controller;

import com.mary_tournament.tournament.model.CategoryGame;
import com.mary_tournament.tournament.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173") // ⚠️ Ajoute le CORS pour Vue.js
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 🔹 GET : Récupérer toutes les catégories
    @GetMapping
    public List<CategoryGame> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 🔹 GET : Récupérer une catégorie par ID
    @GetMapping("/{id}")
    public CategoryGame getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // 🔹 POST : Ajouter une nouvelle catégorie
    @PostMapping
    public CategoryGame createCategory(@RequestBody CategoryGame category) {
        return categoryService.createCategory(category);
    }
}
