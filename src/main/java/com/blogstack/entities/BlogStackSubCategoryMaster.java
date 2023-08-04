package com.blogstack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "blogstack_sub_category_master", schema = "commons")
public class BlogStackSubCategoryMaster implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bsscm_seq_id")
    private Long bsscmSeqId;

    @Column(name = "bsscm_sub_category_id")
    private String bsscmSubCategoryId;

    @Column(name = "bsscm_category_id")
    private String bsscmCategoryId;

    @Column(name = "bsscm_sub_category")
    private String bsscmSubCategory;

    @Column(name = "bsscm_status")
    private String bsscmStatus;

    @CreatedBy
    @Column(name = "bsscm_created_by")
    private String bsscmCreatedBy;

    @CreatedDate
    @Column(name = "bsscm_created_date")
    private LocalDateTime bsscmCreatedDate;

    @LastModifiedBy
    @Column(name = "bsscm_modified_by")
    private String bsscmModifiedBy;

    @LastModifiedDate
    @Column(name = "bsscm_modified_date")
    private LocalDateTime bsscmModifiedDate;
}