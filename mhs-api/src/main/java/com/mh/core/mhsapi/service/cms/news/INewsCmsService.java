package com.mh.core.mhsapi.service.cms.news;

import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.news.NewResponse;

public interface INewsCmsService {
    NewResponse addExtraInfo(NewResponse newResponse);
    Page<NewResponse> search(SearchRequest searchRequest);
}
