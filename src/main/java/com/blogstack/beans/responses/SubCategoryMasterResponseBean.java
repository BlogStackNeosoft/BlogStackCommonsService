package com.blogstack.beans.responses;

import com.blogstack.commons.BlogStackCommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubCategoryMasterResponseBean {

    @JsonProperty(value = "sub_category_id")
    private String subCategoryId;

    @JsonProperty(value = "category_id")
    private String categoryId;

    @JsonProperty(value = "sub_category")
    private String subCategory;

    private String status;

    @JsonProperty(value = "added_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BlogStackCommonConstants.DATE_FORMAT)
    private LocalDateTime addedOn;

    @JsonProperty(value = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BlogStackCommonConstants.DATE_FORMAT)
    private LocalDateTime modifiedOn;
}
