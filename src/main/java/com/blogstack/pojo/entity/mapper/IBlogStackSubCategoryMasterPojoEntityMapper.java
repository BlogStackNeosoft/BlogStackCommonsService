package com.blogstack.pojo.entity.mapper;
import com.blogstack.beans.requests.SubCategoryMasterRequestBean;
import com.blogstack.entities.BlogStackSubCategoryMaster;
import com.blogstack.enums.SubCategoryMasterStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, SubCategoryMasterStatusEnum.class})
public interface IBlogStackSubCategoryMasterPojoEntityMapper {

    IBlogStackSubCategoryMasterPojoEntityMapper INSTANCE = Mappers.getMapper(IBlogStackSubCategoryMasterPojoEntityMapper.class);

    @Mappings({
            @Mapping(target = "bsscmSubCategoryId" , source = "subCategoryMasterRequestBean.subCategoryId"),
            @Mapping(target = "bsscmSubCategory", source = "subCategoryMasterRequestBean.subCategory"),
            @Mapping(target = "bsscmStatus", expression = "java(SubCategoryMasterStatusEnum.ACTIVE.getValue())"),
            @Mapping(target = "bsscmCreatedBy", source = "subCategoryMasterRequestBean.createdBy"),
            @Mapping(target = "bsscmCreatedDate", expression = "java(LocalDateTime.now())")
    })
    BlogStackSubCategoryMaster subCategoryMasterRequestToSubcategoryMasterEntity(SubCategoryMasterRequestBean subCategoryMasterRequestBean);

    public static BiFunction<SubCategoryMasterRequestBean, BlogStackSubCategoryMaster, BlogStackSubCategoryMaster> updateSubCategoryMaster = (subCategoryMasterRequestBean, blogStackSubcategoryMaster) ->  {
       blogStackSubcategoryMaster.setBsscmSubCategoryId(subCategoryMasterRequestBean.getSubCategoryId() != null ? subCategoryMasterRequestBean.getSubCategoryId() : blogStackSubcategoryMaster.getBsscmSubCategoryId());
       blogStackSubcategoryMaster.setBsscmSubCategory(subCategoryMasterRequestBean.getSubCategory() != null ? subCategoryMasterRequestBean.getSubCategory(): blogStackSubcategoryMaster.getBsscmSubCategory());
       blogStackSubcategoryMaster.setBsscmStatus(subCategoryMasterRequestBean.getStatus() != null ? subCategoryMasterRequestBean.getStatus() : blogStackSubcategoryMaster.getBsscmStatus());
       blogStackSubcategoryMaster.setBsscmModifiedBy(subCategoryMasterRequestBean.getModifiedBy());
       blogStackSubcategoryMaster.setBsscmModifiedDate(LocalDateTime.now());
       return blogStackSubcategoryMaster;
    };
}
