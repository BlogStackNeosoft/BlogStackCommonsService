package com.blogstack.beans.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryMasterRequestBean {
    @JsonProperty(value = "category_id")
    private String categoryId;

    @NotNull(message = "Category can not be empty.")
    private String category;

    private String status;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private String modifiedBy;
}