package com.mh.core.mhsapi.service.cms.job_recruiment;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.job_recruitment.JobRecruitmentMapper;
import com.mh.core.mhscommons.data.request.job_recruitment.JobRecruitmentRequest;
import com.mh.core.mhscommons.data.response.job_recruitment.JobRecruitmentResponse;
import com.mh.core.mhscommons.data.tables.pojos.JobRecruitment;
import com.mh.core.mhsrepository.repository.job_recruitment.JobRecruitmentRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class JobRecruimentCmsServiceImp extends AbsCmsService<JobRecruitmentRequest, JobRecruitmentResponse, JobRecruitment, Integer,
        JobRecruitmentRepositoryImp, JobRecruitmentMapper> {

    @Override
    protected String getPermissionCode() {
        return "job_recruitment";
    }

    public JobRecruimentCmsServiceImp(JobRecruitmentRepositoryImp repositoryImp, JobRecruitmentMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }
}
