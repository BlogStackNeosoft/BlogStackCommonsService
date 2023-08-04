package com.blogstack.repository;

import com.blogstack.entities.BlogStackSubCategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBlogStackSubCategoryMasterRepository extends JpaRepository<BlogStackSubCategoryMaster, Long> {

    Optional<BlogStackSubCategoryMaster> findByBsscmSubCategoryIgnoreCase(String subCategory);

    Optional<BlogStackSubCategoryMaster> findByBsscmSubCategoryId(String subCategoryId);
}