package com.elyadata.sm.service;

import com.elyadata.sm.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    Page<CategoryDTO> retrieveAllCategories(Pageable pageable);

    List<CategoryDTO> getAllCategories();

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    long countCategories();

    CategoryDTO getCategoryById(UUID id);
}
