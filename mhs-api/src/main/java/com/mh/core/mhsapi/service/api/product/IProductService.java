package com.mh.core.mhsapi.service.api.product;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.product.ProductRequest;
import com.mh.core.mhscommons.data.response.product.ProductResponse;

public interface IProductService extends IService<ProductRequest, ProductResponse, Integer> {
    Page<ProductResponse> select(SearchRequest searchRequest);
}
