package com.mh.core.mhsapi.service.cms.partner;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.partner.PartnerMapper;
import com.mh.core.mhscommons.data.request.partner.PartnerRequest;
import com.mh.core.mhscommons.data.response.partner.PartnerResponse;
import com.mh.core.mhscommons.data.tables.pojos.Partner;
import com.mh.core.mhsrepository.repository.partner.PartnerRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class PartnerCmsServiceImp extends AbsCmsService<PartnerRequest, PartnerResponse, Partner, Integer, PartnerRepositoryImp, PartnerMapper> {


    @Override
    protected String getPermissionCode() {
        return "partner";
    }

    public PartnerCmsServiceImp(PartnerRepositoryImp repositoryImp, PartnerMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }
}
