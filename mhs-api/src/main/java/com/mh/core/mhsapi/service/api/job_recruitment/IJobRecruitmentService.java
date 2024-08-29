package com.mh.core.mhsapi.service.api.job_recruitment;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.job_recruitment.JobRecruitmentRequest;
import com.mh.core.mhscommons.data.response.job_recruitment.JobRecruitmentResponse;
import com.mh.core.mhscommons.data.tables.pojos.JobRecruitment;

public interface IJobRecruitmentService extends IService<JobRecruitmentRequest, JobRecruitmentResponse, Integer> {
    Page<JobRecruitmentResponse> select(SearchRequest searchRequest);
}
