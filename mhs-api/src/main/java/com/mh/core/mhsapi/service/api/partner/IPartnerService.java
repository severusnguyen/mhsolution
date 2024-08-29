package com.mh.core.mhsapi.service.api.partner;

import com.mh.core.mhsapi.service.api.IService;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.partner.PartnerRequest;
import com.mh.core.mhscommons.data.response.partner.PartnerResponse;

public interface IPartnerService extends IService<PartnerRequest, PartnerResponse, Integer> {

    Page<PartnerResponse> select(SearchRequest searchRequest);

}
