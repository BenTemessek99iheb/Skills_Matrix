package com.elyadata.sm.service.impl;

import com.elyadata.sm.dto.CategoryDTO;
import com.elyadata.sm.mapper.CategoryMapper;
import com.elyadata.sm.model.Category;
import com.elyadata.sm.repository.ICategoryRepo;
import com.elyadata.sm.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepo iCategoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDTO> retrieveAllCategories(Pageable pageable) {
        return iCategoryRepo.findAll(pageable).map(categoryMapper::toDto);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return iCategoryRepo.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {

        return categoryMapper.toDto(iCategoryRepo.save(categoryMapper.toEntity(categoryDTO)));
    }
    //count categories
    public long countCategories() {
        return iCategoryRepo.count();
    }
    //get category by id
    public CategoryDTO getCategoryById(UUID id) {
        return categoryMapper.toDto(iCategoryRepo.findById(id).orElseThrow());
    }
}
