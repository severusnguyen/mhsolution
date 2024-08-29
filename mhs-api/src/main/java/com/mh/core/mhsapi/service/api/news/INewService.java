package com.mh.core.mhsapi.service.api.news;

import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.news.NewResponse;

public interface INewService {
    Page<NewResponse> searchNew(SearchRequest searchRequest);

    Page<NewResponse> select(SearchRequest searchRequest);
}
