package com.mh.core.mhsapi.service.api.co_founder;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.co_founder.CoFounderRequest;
import com.mh.core.mhscommons.data.response.co_founder.CoFounderResponse;

public interface ICoFounderService extends IService<CoFounderRequest, CoFounderResponse, Integer> {

    Page<CoFounderResponse> select(SearchRequest searchRequest);

}
