package com.mh.core.mhsapi.service.api.applicant;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.applicant.ApplicantRequest;
import com.mh.core.mhscommons.data.response.applicant.ApplicantResponse;

public interface IApplicantService extends IService<ApplicantRequest, ApplicantResponse, Integer> {

    Page<ApplicantResponse> select(SearchRequest searchRequest);

}
