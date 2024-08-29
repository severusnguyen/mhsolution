package com.mh.core.mhscommons.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.model.paging.Order;
import static com.mh.core.mhscommons.data.model.paging.Pageable.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SearchRequest {
    private int page;
    private int pageSize = 10;
    private String keyword;
    private List<Order> sorts;
    private List<Filter> filters;

    @JsonIgnore
    public Integer getOffset() {
        return Math.max((page - 1) * pageSize, 0);
    }

    public int getPageSize() {
        if (this.pageSize < 0) return MAXIMUM_PAGE_SIZE;
        return pageSize;
    }
}
