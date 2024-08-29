package com.mh.core.mhscommons.data.request.news;

import com.mh.core.mhscommons.data.model.Seo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewRequest {
    private String title;
    private String content;
    private Seo seo;
    private Integer categoryNewId;
    private Long createdAt;
    private String coverUrl;
    private Integer createdBy;
    private Short isHighlights;
    private Integer createBy;
    private Integer deleteBy;
}
