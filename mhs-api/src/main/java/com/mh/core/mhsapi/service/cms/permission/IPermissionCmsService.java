package com.mh.core.mhsapi.service.cms.permission;

import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.response.permission.PermissionResponse;

import java.util.List;

public interface IPermissionCmsService {
    Page<PermissionResponse> search(SearchRequest searchRequest);
    List<PermissionResponse> getAll();
}
