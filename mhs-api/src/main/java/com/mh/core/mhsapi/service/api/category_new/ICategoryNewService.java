package com.mh.core.mhsapi.service.api.category_new;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.category_new.CategoryNewRequest;
import com.mh.core.mhscommons.data.response.category_new.CategoryNewResponse;
import com.mh.core.mhscommons.data.tables.pojos.CategoryNew;

import java.util.List;
import java.util.Map;

public interface ICategoryNewService extends IService<CategoryNewRequest, CategoryNewResponse, Integer> {

    Page<CategoryNewResponse> select(SearchRequest searchRequest);

    Map<Integer, CategoryNew> getMap(List<Integer> categoryNewIds);

    Map<Integer, CategoryNewResponse> getMapCategoryNewResponseByIds(List<Integer> categoryNewIds);

    CategoryNewResponse getCategoryNewResponseById(Integer id);

}
