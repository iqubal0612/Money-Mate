package com.imam.moneymate.service;

import com.imam.moneymate.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO saveCategory(CategoryDTO categoryDTO);

    public List<CategoryDTO> getCategoriesForCurrentUser();

    public List<CategoryDTO> getCategoriesByTypeForCurrentUser(String type);

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
}
