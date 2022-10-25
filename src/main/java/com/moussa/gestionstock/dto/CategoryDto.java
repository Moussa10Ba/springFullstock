package com.moussa.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moussa.gestionstock.model.Article;
import com.moussa.gestionstock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<Article> articles;


    public static CategoryDto fromEntity(Category category){
        if (category == null){
            return null;
        }
        return  CategoryDto.builder()
                .code(category.getCode())
                .designation(category.getDesignation())
                .id(category.getId())
                .idEntreprise(category.getIdEntreprise())
                .build();

    }


    public static Category toEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            return  null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.designation);
        return category;
    }
}
