package com.mh.core.mhsapi.service.api.home_page;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.home_page.HomePageRequest;
import com.mh.core.mhscommons.data.response.home_page.HomePageResponse;

public interface IHomePageService extends IService<HomePageRequest, HomePageResponse, Integer> {
    Page<HomePageResponse> select(SearchRequest searchRequest);
}
