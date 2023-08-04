package com.blogstack.beans.responses;


import com.blogstack.commons.BlogStackCommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryMasterResponseBean {
    @JsonProperty(value = "category_id")
    private String categoryId;

    @NotNull(message = "category cannot be empty.")
    private String category;

    @JsonProperty(value = "sub_category")
    private Set<SubCategoryMasterResponseBean> blogStackSubCategory;

    private String status;

    @JsonProperty(value = "added_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = BlogStackCommonConstants.DATE_FORMAT)
    private LocalDateTime addedOn;

    @JsonProperty(value = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BlogStackCommonConstants.DATE_FORMAT)
    private LocalDateTime modifiedOn;
}