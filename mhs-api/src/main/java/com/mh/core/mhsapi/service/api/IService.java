package com.mh.core.mhsapi.service.api;

import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;

import java.util.List;

public interface IService <Rq, Rp, ID>{
    Rp insert(Rq request);
    String insert(List<Rq> requestList);
    Rp update(ID id, Rq request);
    Rp findById(ID id);
    Page<Rp> search(SearchRequest searchRequest);
    Rp delete(ID id);
}
