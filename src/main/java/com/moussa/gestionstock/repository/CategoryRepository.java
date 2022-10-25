package com.moussa.gestionstock.repository;

import com.moussa.gestionstock.dto.CategoryDto;
import com.moussa.gestionstock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface
CategoryRepository extends JpaRepository<Category, Integer> {
   /* @Query("select cat from category where code = :code")
    Category finByCustumRequest(String code);
    @Query(value = "select * from category where code =: code ", nativeQuery = true)
    Category finByNativeRequest(String code);
    */
    Optional<Category> findByCode(String code);
}
