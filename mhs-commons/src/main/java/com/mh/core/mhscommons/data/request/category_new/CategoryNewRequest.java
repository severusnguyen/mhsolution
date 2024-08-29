package com.mh.core.mhscommons.data.request.category_new;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryNewRequest {
    private String title;
    private String content;
    private Integer position;
    private Integer createBy;
    private Integer deleteBy;
}
