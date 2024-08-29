package com.mh.core.mhsapi.service.api.contact;

import com.mh.core.mhsapi.service.api.AbsService;
import com.mh.core.mhscommons.data.mappers.contact.ContactMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.contact.ContactRequest;
import com.mh.core.mhscommons.data.response.contact.ContactResponse;
import com.mh.core.mhscommons.data.tables.pojos.Contact;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.contact.ContactRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mh.core.mhscommons.data.constant.message.contact.ContactMessageConstant.*;


@Service
@Slf4j
public class ContactServiceImp extends AbsService<ContactRequest, ContactResponse, Contact, Integer, ContactRepositoryImp, ContactMapper>
        implements IContactService {

    @Autowired
    public ContactServiceImp(ContactRepositoryImp repository, ContactMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ContactResponse findById(Integer integer) {
        try {
            Contact contact = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(CONTACT_NOT_FOUND, 404));
            ContactResponse contactResponse = mapper.toResponse(contact);
            return contactResponse;
        } catch (ApiException apiException) {
            log.error("[ERROR] findById {} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage(), apiException.getCode());
        }
    }

    @Override
    public Page<ContactResponse> search(SearchRequest searchRequest) {
        try {
            List<Contact> contacts = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<ContactResponse> contactResponses = new ArrayList<>();
            if (total != 0) {
                contactResponses = mapper.toResponses(contacts);
            }
            return new Page<ContactResponse>()
                    .setTotal(total)
                    .setItems(contactResponses);
        } catch (Exception exception) {
            log.error("[ERROR] search {} " + exception.getMessage());
            return new Page<ContactResponse>()
                    .setTotal(0L)
                    .setItems(new ArrayList<>());
        }
    }

    @Override
    public Page<ContactResponse> select(SearchRequest searchRequest) {
        try {
            List<Contact> contacts = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            List<ContactResponse> contactResponses = new ArrayList<>();
            if (total != 0){
                contactResponses = mapper.toResponses(contacts);
            }
            return new Page<>(total, searchRequest, contactResponses);
        } catch (Exception exception) {
            log.error("[ERROR] select {} " + exception.getMessage());
            return new Page<>(0L, searchRequest, new ArrayList<>());
        }
    }
}
