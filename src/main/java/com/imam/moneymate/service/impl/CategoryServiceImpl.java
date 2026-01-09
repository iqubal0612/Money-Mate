package com.imam.moneymate.service.impl;

import com.imam.moneymate.dto.CategoryDTO;
import com.imam.moneymate.entity.Category;
import com.imam.moneymate.entity.Profile;
import com.imam.moneymate.repository.CategoryRepository;
import com.imam.moneymate.service.CategoryService;
import com.imam.moneymate.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ProfileService profileService;

    private final CategoryRepository categoryRepository;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Profile profile = profileService.getcurrentProfile();
        if (categoryRepository.existsByNameAndProfileId(categoryDTO.getName(), profile.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with name already exist");
        }

        Category newCategory = toEntity(categoryDTO, profile);
        newCategory = categoryRepository.save(newCategory);

        return toDTO(newCategory);
    }

    public List<CategoryDTO> getCategoriesForCurrentUser(){
        Profile profile = profileService.getcurrentProfile();
        List<Category> categories = categoryRepository.findByProfileId(profile.getId());

        return categories.stream().map(this::toDTO).toList();
    }

    public List<CategoryDTO> getCategoriesByTypeForCurrentUser(String type) {
        Profile profile = profileService.getcurrentProfile();

        return categoryRepository.findByTypeAndProfileId(type, profile.getId())
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Profile profile = profileService.getcurrentProfile();
        Category existingcategory = categoryRepository.findByIdAndProfileId(categoryId, profile.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found not accessible"));

        existingcategory.setName(categoryDTO.getName());
        existingcategory.setIcon(categoryDTO.getIcon());
        categoryRepository.save(existingcategory);

        return toDTO(existingcategory);
    }

    private Category toEntity(CategoryDTO categoryDTO, Profile profile) {

        return Category.builder()
                .name(categoryDTO.getName())
                .icon(categoryDTO.getIcon())
                .profile(profile)
                .type(categoryDTO.getType())
                .build();
    }

    private CategoryDTO toDTO(Category category) {

        return CategoryDTO.builder()
                .id(category.getId())
                .profileId(category.getProfile() != null ? category.getProfile().getId() : null)
                .name(category.getName())
                .icon(category.getIcon())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .type(category.getType())
                .build();
    }
}
