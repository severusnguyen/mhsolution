package com.mh.core.mhsapi.service.cms.permission;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.mappers.permission.PermissionMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.permission.PermissionRequest;
import com.mh.core.mhscommons.data.response.permission.PermissionResponse;
import com.mh.core.mhscommons.data.tables.pojos.Permission;
import com.mh.core.mhscommons.utils.CollectionUtils;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.permission.PermissionRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionCmsServiceImp extends AbsCmsService<PermissionRequest, PermissionResponse, Permission, Integer,
        PermissionRepositoryImp, PermissionMapper> implements IPermissionCmsService{

    @Override
    protected String getPermissionCode() {
        return "permission";
    }

    public PermissionCmsServiceImp(PermissionRepositoryImp repositoryImp, PermissionMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Override
    public Page<PermissionResponse> search(SearchRequest searchRequest) {
        List<Permission> permissions = repository.search(searchRequest);
        Long total = repository.count(searchRequest);
        List<Integer> perIds = CollectionUtils.extractField(permissions, Permission::getId);
        List<Permission> detailPermission = repository.getPermissionById(perIds);
        List<PermissionResponse> responses = mapper.toResponses(detailPermission);

        return new Page<PermissionResponse>()
                .setTotal(total)
                .setItems(responses);
    }

    @Override
    public List<PermissionResponse> getAll() {
        List<Permission> permissions = repository.getAllPermission();
        return permissions.stream()
                .map(permission -> mapper.toResponse(permission))
                .collect(Collectors.toList());
    }
}
