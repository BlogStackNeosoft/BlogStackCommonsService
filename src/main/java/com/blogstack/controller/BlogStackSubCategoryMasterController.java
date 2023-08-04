package com.blogstack.controller;

import com.blogstack.beans.requests.SubCategoryMasterRequestBean;
import com.blogstack.service.IBlogStackSubCategoryMasterService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("${commons-service-version}/sub-category")
@AllArgsConstructor
public class BlogStackSubCategoryMasterController {

    private final IBlogStackSubCategoryMasterService blogStackSubcategoryMasterService;

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<?> addSubcategory(@PathVariable("categoryId") @NotBlank(message = "Category Id can not be blank") String categoryId ,@Valid @RequestBody SubCategoryMasterRequestBean subCategoryMasterRequestBean){
        return ResponseEntity.ok(this.blogStackSubcategoryMasterService.addSubcategory(categoryId, subCategoryMasterRequestBean));
    }

    @GetMapping("/")
    public ResponseEntity<?> fetchAllSubcategory(@RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity.ok(this.blogStackSubcategoryMasterService.fetchAllSubcategories(page, size));
    }

    @GetMapping("/{subCategory_id}")
    public ResponseEntity<?> fetchSubcategoryById(@PathVariable(value = "subCategory_id") @NotBlank(message = "Subcategory Id can not be empty.") String subCategoryId){
        return ResponseEntity.ok(this.blogStackSubcategoryMasterService.fetchSubcategoryById(subCategoryId));
    }

    @PutMapping("/")
    public ResponseEntity<?> updateSubcategory(@Valid @RequestBody SubCategoryMasterRequestBean subCategoryMasterRequestBean){
        return ResponseEntity.ok(this.blogStackSubcategoryMasterService.updateSubcategory(subCategoryMasterRequestBean));
    }

    @DeleteMapping("/{subCategory_id}")
    public ResponseEntity<?> deleteSubcategory(@PathVariable(value = "subCategory_id") @NotBlank(message = "Subcategory Id can not be empty.") String subCategoryId){
        return ResponseEntity.ok(this.blogStackSubcategoryMasterService.deleteSubcategory(subCategoryId));
    }

    @GetMapping("/category_id/{category_id}")
    public ResponseEntity<?> fetchAllSubcategoriesByCategoryId(@PathVariable(value = "category_id") @NotBlank(message = "category Id can not be empty.") String categoryId){
        return ResponseEntity.ok(this.blogStackSubcategoryMasterService.fetchAllSubcategoriesByCategoryId(categoryId));
    }
}