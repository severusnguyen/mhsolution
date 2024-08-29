package com.mh.core.mhsapi.service.cms.cooperation_contact;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.cooperation_contact.CooperationContactMapper;
import com.mh.core.mhscommons.data.request.cooperation_contact.CooperationContactRequest;
import com.mh.core.mhscommons.data.response.cooperation_contact.CooperationContactResponse;
import com.mh.core.mhscommons.data.tables.pojos.CooperationContact;
import com.mh.core.mhsrepository.repository.cooperation_contact.CooperationContactRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class CooperationContactCmsServiceImp extends AbsCmsService<CooperationContactRequest, CooperationContactResponse, CooperationContact,
        Integer, CooperationContactRepositoryImp, CooperationContactMapper> {

    @Override
    protected String getPermissionCode() {
        return "cooperation_contact";
    }

    public CooperationContactCmsServiceImp(CooperationContactRepositoryImp repositoryImp, CooperationContactMapper mapper) {
        this.repository =repositoryImp;
        this.mapper = mapper;
    }
}
