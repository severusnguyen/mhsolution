package com.mh.core.mhsapi.service.cms.applicant;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.applicant.ApplicantMapper;
import com.mh.core.mhscommons.data.request.applicant.ApplicantRequest;
import com.mh.core.mhscommons.data.response.applicant.ApplicantResponse;
import com.mh.core.mhscommons.data.tables.pojos.Applicant;
import com.mh.core.mhsrepository.repository.applicant.ApplicantRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class ApplicantCmsServiceImp extends AbsCmsService<ApplicantRequest, ApplicantResponse, Applicant, Integer,
        ApplicantRepositoryImp, ApplicantMapper> implements  IApplicantCmsService{

    @Override
    protected String getPermissionCode() {
        return "applicant";
    }

    public ApplicantCmsServiceImp(ApplicantRepositoryImp repositoryImp, ApplicantMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }
}
