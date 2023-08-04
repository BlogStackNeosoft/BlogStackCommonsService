package com.blogstack.service;

import com.blogstack.beans.requests.SubCategoryMasterRequestBean;
import com.blogstack.beans.responses.ServiceResponseBean;

import java.util.Optional;

public interface IBlogStackSubCategoryMasterService {

    Optional<ServiceResponseBean> addSubcategory(String categoryId, SubCategoryMasterRequestBean subCategoryMasterRequestBean);

    Optional<ServiceResponseBean> fetchSubcategoryById(String subCategoryId);

    Optional<ServiceResponseBean> fetchAllSubcategories(Integer page, Integer size);

    Optional<ServiceResponseBean> fetchAllSubcategoriesByCategoryId(String categoryId);

    Optional<ServiceResponseBean> deleteSubcategory(String subCategoryId);

    Optional<ServiceResponseBean> updateSubcategory(SubCategoryMasterRequestBean subCategoryMasterRequestBean);
}