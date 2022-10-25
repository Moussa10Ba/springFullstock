package com.moussa.gestionstock.service;

import com.moussa.gestionstock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findByCode(String code);

    CategoryDto findById(Integer id);

    List<CategoryDto> findAll();

    void delete(Integer id);
}
