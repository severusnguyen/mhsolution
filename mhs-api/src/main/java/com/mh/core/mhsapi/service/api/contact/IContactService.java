package com.mh.core.mhsapi.service.api.contact;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.contact.ContactRequest;
import com.mh.core.mhscommons.data.response.contact.ContactResponse;

public interface IContactService extends IService<ContactRequest, ContactResponse, Integer> {
    Page<ContactResponse> select(SearchRequest searchRequest);
}
