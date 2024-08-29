package com.mh.core.mhsapi.service.cms.contact;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.contact.ContactMapper;
import com.mh.core.mhscommons.data.request.contact.ContactRequest;
import com.mh.core.mhscommons.data.response.contact.ContactResponse;
import com.mh.core.mhscommons.data.tables.pojos.Contact;
import com.mh.core.mhsrepository.repository.contact.ContactRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class ContactCmsServiceImp extends AbsCmsService<ContactRequest, ContactResponse, Contact, Integer, ContactRepositoryImp, ContactMapper> {

    @Override
    protected String getPermissionCode() {
        return "contacts";
    }

    public ContactCmsServiceImp(ContactRepositoryImp repositoryImp, ContactMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }
}
