package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.CategoryDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.model.Category;
import com.moussa.gestionstock.repository.CategoryRepository;
import com.moussa.gestionstock.service.CategoryService;
import com.moussa.gestionstock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()){
            log.error("Categorie invalid {}", categoryDto);
            throw new InvalidEntityException("La category n'est pas valid", ErrorCodes.CATEGORY_NOT_VALID);
        }
      //  Category savedCategory = categoryRepository.save(CategoryDto.toEntity(categoryDto));
      //  return CategoryDto.fromEntity(savedCategory);
        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Code is null");
            return null;
        }
       return categoryRepository.findByCode(code)
               .map(CategoryDto::fromEntity)
               .orElseThrow( () -> new EntityNotFoundException("La categorie avec le code "+ code + " n'existe pas", ErrorCodes.CATEGORY_NOT_FOUND));


    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null){
            log.error("Category ID is null");
            return null;
        }
        return categoryRepository.findById(id).map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune  categorie avec l'id " + id + "n'existe en BDD" + ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
            if (id == null){
                log.error("Id is null");
                return;
            }
            categoryRepository.deleteById(id);
    }
}
