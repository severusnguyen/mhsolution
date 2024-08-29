package com.mh.core.mhsapi.service.api.cooperation_contact;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.cooperation_contact.CooperationContactRequest;
import com.mh.core.mhscommons.data.response.cooperation_contact.CooperationContactResponse;

public interface ICooperationContactService extends IService<CooperationContactRequest, CooperationContactResponse, Integer> {
    Page<CooperationContactResponse> select(SearchRequest searchRequest);
}
