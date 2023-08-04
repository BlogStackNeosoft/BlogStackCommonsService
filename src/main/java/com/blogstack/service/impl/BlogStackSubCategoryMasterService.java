package com.blogstack.service.impl;

import com.blogstack.beans.requests.SubCategoryMasterRequestBean;
import com.blogstack.beans.responses.PageResponseBean;
import com.blogstack.beans.responses.ServiceResponseBean;
import com.blogstack.commons.BlogStackMessageConstants;
import com.blogstack.entities.BlogStackCategoryMaster;
import com.blogstack.entities.BlogStackSubCategoryMaster;
import com.blogstack.entity.pojo.mapper.IBlogStackSubCategoryMasterEntityPojoMapper;
import com.blogstack.enums.SubCategoryMasterStatusEnum;
import com.blogstack.enums.UuidPrefixEnum;
import com.blogstack.exceptions.BlogStackCustomException;
import com.blogstack.exceptions.BlogStackDataNotFoundException;
import com.blogstack.pojo.entity.mapper.IBlogStackSubCategoryMasterPojoEntityMapper;
import com.blogstack.repository.IBlogStackCategoryMasterRepository;
import com.blogstack.repository.IBlogStackSubCategoryMasterRepository;
import com.blogstack.service.IBlogStackSubCategoryMasterService;
import com.blogstack.utils.BlogStackCommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogStackSubCategoryMasterService implements IBlogStackSubCategoryMasterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogStackSubCategoryMasterService.class);

    @Autowired
    private IBlogStackSubCategoryMasterRepository blogStackSubcategoryMasterRepository;

    @Autowired
    private IBlogStackCategoryMasterRepository blogStackCategoryMasterRepository;

    @Value("#{'${spring.application.name}'.toUpperCase()}")
    private String springApplicationName;

    @Autowired
    private IBlogStackSubCategoryMasterPojoEntityMapper blogStackSubcategoryMasterPojoEntityMapper;

    @Override
    public Optional<ServiceResponseBean> addSubcategory(String categoryId, SubCategoryMasterRequestBean subCategoryMasterRequestBean) {
        Optional<BlogStackSubCategoryMaster> blogStackSubcategoryMasterOptional = this.blogStackSubcategoryMasterRepository.findByBsscmSubCategoryIgnoreCase(subCategoryMasterRequestBean.getSubCategory());
        LOGGER.warn("BlogStackSubcategoryMasterOptional :: {}", blogStackSubcategoryMasterOptional);

        if (blogStackSubcategoryMasterOptional.isPresent())
            throw new BlogStackCustomException(BlogStackMessageConstants.INSTANCE.ALREADY_EXIST);

        String subCategoryId = BlogStackCommonUtils.INSTANCE.uniqueIdentifier(UuidPrefixEnum.SUBCATEGORY_ID.getValue());
        LOGGER.warn("SubcategoryId :: {}", subCategoryId);

        subCategoryMasterRequestBean.setSubCategoryId(subCategoryId);
        subCategoryMasterRequestBean.setCreatedBy(springApplicationName);
        Optional<BlogStackCategoryMaster> blogStackCategoryMasterOptional = this.blogStackCategoryMasterRepository.findByBscmCategoryId(categoryId);
        LOGGER.warn("BlogStackCategoryMasterOptional :: {}", blogStackCategoryMasterOptional);

        BlogStackSubCategoryMaster blogStackSubCategoryMaster = IBlogStackSubCategoryMasterPojoEntityMapper.INSTANCE.subCategoryMasterRequestToSubcategoryMasterEntity(subCategoryMasterRequestBean);

        if (blogStackCategoryMasterOptional.isEmpty())
            throw new BlogStackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);


        Optional<BlogStackCategoryMaster> blogStackCategorySubcategoryMasterOptional = blogStackCategoryMasterOptional.map(category -> {
            category.getBlogStackSubCategoryMasterList().add(blogStackSubCategoryMaster);
            return this.blogStackCategoryMasterRepository.saveAndFlush(category);
        });

        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).data(IBlogStackSubCategoryMasterEntityPojoMapper.mapSubCategoryMasterEntityPojoMapping.apply(blogStackSubCategoryMaster)).build());
    }

    @Override
    public Optional<ServiceResponseBean> fetchSubcategoryById(String subCategoryId) {
        Optional<BlogStackSubCategoryMaster> blogStackSubcategoryMasterOptional = this.blogStackSubcategoryMasterRepository.findByBsscmSubCategoryId(subCategoryId);

        if (blogStackSubcategoryMasterOptional.isEmpty())
            throw new BlogStackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).data(IBlogStackSubCategoryMasterEntityPojoMapper.mapSubCategoryMasterEntityPojoMapping.apply(blogStackSubcategoryMasterOptional.get())).build());
    }

    @Override
    public Optional<ServiceResponseBean> fetchAllSubcategories(Integer page, Integer size) {
        Page<BlogStackSubCategoryMaster> blogStackSubcategoryMasterPage = this.blogStackSubcategoryMasterRepository.findAll(PageRequest.of(page, size));
        LOGGER.debug("BlogStackQuestionMaster :: {}", blogStackSubcategoryMasterPage);

        if (CollectionUtils.isEmpty(blogStackSubcategoryMasterPage.toList()))
            throw new BlogStackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

        return Optional.of(ServiceResponseBean.builder()
                .status(Boolean.TRUE).data(PageResponseBean.builder().payload(IBlogStackSubCategoryMasterEntityPojoMapper.mapSubcategoryMasterEntityListToPojoListMapping.apply(blogStackSubcategoryMasterPage.toSet()))
                        .numberOfElements(blogStackSubcategoryMasterPage.getNumberOfElements())
                        .pageSize(blogStackSubcategoryMasterPage.getSize())
                        .totalElements(blogStackSubcategoryMasterPage.getTotalElements())
                        .totalPages(blogStackSubcategoryMasterPage.getTotalPages())
                        .currentPage(blogStackSubcategoryMasterPage.getNumber())
                        .build()).build());
    }

    @Override
    public Optional<ServiceResponseBean> fetchAllSubcategoriesByCategoryId(String categoryId) {
        Optional<BlogStackCategoryMaster> blogStackCategoryMasterOptional = this.blogStackCategoryMasterRepository.findByBscmCategoryId(categoryId);
        LOGGER.warn("BlogStackCategoryMasterOptional :: {}", blogStackCategoryMasterOptional);

        if (blogStackCategoryMasterOptional.isEmpty())
            throw new BlogStackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);
        Set<BlogStackSubCategoryMaster> blogStackSubCategoryMasterList = blogStackCategoryMasterOptional.get().getBlogStackSubCategoryMasterList();
        LOGGER.warn("BlogStackSubcategoryMasterList :: {}", blogStackSubCategoryMasterList);

        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).data(IBlogStackSubCategoryMasterEntityPojoMapper.mapSubcategoryMasterEntityListToPojoListMapping.apply(blogStackSubCategoryMasterList)).build());
    }

    @Override
    public Optional<ServiceResponseBean> updateSubcategory(SubCategoryMasterRequestBean subCategoryMasterRequestBean) {
        Optional<BlogStackSubCategoryMaster> blogStackSubcategoryMasterOptional = this.blogStackSubcategoryMasterRepository.findByBsscmSubCategoryId(subCategoryMasterRequestBean.getSubCategoryId());
        LOGGER.warn("BlogStackSubcategoryMasterOptional :: {}", blogStackSubcategoryMasterOptional);

        if (blogStackSubcategoryMasterOptional.isEmpty())
            throw new BlogStackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

        subCategoryMasterRequestBean.setModifiedBy(this.springApplicationName);
        BlogStackSubCategoryMaster blogStackSubcategoryMaster = IBlogStackSubCategoryMasterPojoEntityMapper.updateSubCategoryMaster.apply(subCategoryMasterRequestBean, blogStackSubcategoryMasterOptional.get());
        LOGGER.debug("blogStackSubcategoryMaster :: {}", blogStackSubcategoryMaster);

        this.blogStackSubcategoryMasterRepository.saveAndFlush(blogStackSubcategoryMaster);
        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).data(IBlogStackSubCategoryMasterEntityPojoMapper.mapSubCategoryMasterEntityPojoMapping.apply(blogStackSubcategoryMaster)).build());
    }

    @Override
    public Optional<ServiceResponseBean> deleteSubcategory(String subCategoryId) {
        Optional<BlogStackSubCategoryMaster> blogStackSubcategoryMasterOptional = this.blogStackSubcategoryMasterRepository.findByBsscmSubCategoryId(subCategoryId);
        LOGGER.warn("blogStackSubcategoryMasterOptional :: {}", blogStackSubcategoryMasterOptional);

        if (blogStackSubcategoryMasterOptional.isEmpty())
            throw new BlogStackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

        blogStackSubcategoryMasterOptional.get().setBsscmStatus(SubCategoryMasterStatusEnum.DELETED.getValue());
        blogStackSubcategoryMasterOptional.get().setBsscmModifiedBy(springApplicationName);
        this.blogStackSubcategoryMasterRepository.saveAndFlush(blogStackSubcategoryMasterOptional.get());
        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).message(BlogStackMessageConstants.INSTANCE.DATA_DELETED).build());
    }
}