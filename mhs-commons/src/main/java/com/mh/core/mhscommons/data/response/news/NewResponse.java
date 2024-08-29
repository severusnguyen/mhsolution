package com.mh.core.mhscommons.data.response.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.model.Seo;
import com.mh.core.mhscommons.data.response.BaseResponse;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewResponse extends BaseResponse {
    private Integer id;
    private String seoId;
    private Seo seo;
    private String title;
    private String content;
    private Integer categoryNewId;
    private Long createdAt;
    private String coverUrl;
    private Integer createdBy;
    private Short isHighlights;
    private CategoryNewResponse category;
    private Long numView;
    private String categoryTitle;
}
