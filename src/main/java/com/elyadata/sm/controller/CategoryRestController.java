package com.elyadata.sm.controller;

import com.elyadata.sm.dto.CategoryDTO;
import com.elyadata.sm.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Categories Managment")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryRestController {
    private final ICategoryService categoryService;

    @Operation(description = "Retrieve all categories pagable")
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getCategories(@Parameter Pageable pageable) {
        return ResponseEntity.ok(categoryService.retrieveAllCategories(pageable));
    }
    @Operation(description = "Retrieve all categories")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @Operation(description = "add new category ")
    @PostMapping
    public CategoryDTO addCategory(@RequestBody CategoryDTO c) {

        return categoryService.addCategory(c);
    }

    /* @PostMapping("/populate-from-json")
    public ResponseEntity<String> populateFromJson() {
        try {
            dataSyncService.syncData();
            return ResponseEntity.ok("Data populated successfully from JSON!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error populating data from JSON: " + e.getMessage());
        }
    }*/




    //count categories
    @Operation(description = "count categories")
    @GetMapping("/count")
    public ResponseEntity<Long> countCategories() {
        return ResponseEntity.ok(categoryService.countCategories());
    }
    //get categoryById
    @Operation(description = "Retrieve  category by id ")
    @GetMapping("/{id}")
    public CategoryDTO retrieveCategory(@PathVariable UUID id) {
        return categoryService.getCategoryById(id);
    }
}
