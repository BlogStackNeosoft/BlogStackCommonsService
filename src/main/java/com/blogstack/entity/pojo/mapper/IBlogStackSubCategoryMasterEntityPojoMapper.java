package com.blogstack.entity.pojo.mapper;

import com.blogstack.beans.responses.SubCategoryMasterResponseBean;
import com.blogstack.entities.BlogStackSubCategoryMaster;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface IBlogStackSubCategoryMasterEntityPojoMapper {
    public static Function<BlogStackSubCategoryMaster, SubCategoryMasterResponseBean> mapSubCategoryMasterEntityPojoMapping = blogStackSubcategoryMaster -> SubCategoryMasterResponseBean.builder()
            .subCategoryId(blogStackSubcategoryMaster.getBsscmSubCategoryId())
            .subCategory(blogStackSubcategoryMaster.getBsscmSubCategory())
            .status(blogStackSubcategoryMaster.getBsscmStatus())
            .addedOn(blogStackSubcategoryMaster.getBsscmCreatedDate())
            .build();


    public static Function<Set<BlogStackSubCategoryMaster>, Set<SubCategoryMasterResponseBean>> mapSubcategoryMasterEntityListToPojoListMapping = blogStackSubcategoryMasterList -> blogStackSubcategoryMasterList.stream()
            .map(blogStackSubcategoryMaster -> {
                SubCategoryMasterResponseBean.SubCategoryMasterResponseBeanBuilder subCategoryMasterResponseBeanBuilder = SubCategoryMasterResponseBean.builder();
                subCategoryMasterResponseBeanBuilder.subCategoryId(blogStackSubcategoryMaster.getBsscmSubCategoryId())
                        .subCategory(blogStackSubcategoryMaster.getBsscmSubCategory())
                        .status(blogStackSubcategoryMaster.getBsscmStatus())
                        .addedOn(blogStackSubcategoryMaster.getBsscmCreatedDate());
                return subCategoryMasterResponseBeanBuilder.build();
            }).collect(Collectors.toSet());
}
