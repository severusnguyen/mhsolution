package com.mh.core.mhsapi.service.api.introduction;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.introduction.IntroductionRequest;
import com.mh.core.mhscommons.data.response.introduction.IntroductionResponse;


public interface IIntroductionService extends IService<IntroductionRequest, IntroductionResponse, Integer> {
    Page<IntroductionResponse> select(SearchRequest searchRequest);
}
