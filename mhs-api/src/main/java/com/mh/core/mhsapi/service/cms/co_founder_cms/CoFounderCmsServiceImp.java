package com.mh.core.mhsapi.service.cms.co_founder_cms;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.co_founder.CoFounderMapper;
import com.mh.core.mhscommons.data.request.co_founder.CoFounderRequest;
import com.mh.core.mhscommons.data.response.co_founder.CoFounderResponse;
import com.mh.core.mhscommons.data.tables.pojos.CoFounder;
import com.mh.core.mhsrepository.repository.co_founder.CoFounderRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoFounderCmsServiceImp extends AbsCmsService<CoFounderRequest, CoFounderResponse, CoFounder, Integer, CoFounderRepositoryImp, CoFounderMapper>
        implements ICoFounderCmsService {
    @Override
    protected String getPermissionCode() {
        return "co-founder";
    }

    @Autowired
    public CoFounderCmsServiceImp(CoFounderRepositoryImp repository, CoFounderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


}
