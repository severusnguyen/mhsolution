package com.mh.core.mhsapi.service.cms;

import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhsconfig.config.exception.ApiException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ICmsService<Rq, Rp, ID>{
    Rp insert(Rq request, Authentication authentication) throws ApiException;
    Rp update(ID id, Rq request, Authentication authentication) throws ApiException;
    Rp findById(ID id, Authentication authentication) throws ApiException;
    MessageResponse deleteById(ID id, Authentication authentication) throws ApiException;
   Page<Rp> search(SearchRequest searchRequest);
}
