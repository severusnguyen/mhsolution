package com.mh.core.mhsapi.service.cms.role;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.constant.message.role.RoleConstant;
import com.mh.core.mhscommons.data.mappers.role.RoleMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.role.RoleRequest;
import com.mh.core.mhscommons.data.request.role_permission.RolePermissionRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.role.RoleResponse;
import com.mh.core.mhscommons.data.tables.pojos.Permission;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.pojos.RolePermission;
import com.mh.core.mhscommons.utils.CollectionUtils;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.role.RoleRepositoryImp;
import com.mh.core.mhsrepository.repository.role_permission.RolePermisionRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mh.core.mhscommons.data.constant.message.role.RoleConstant.ROLE_NOT_FOUND;

@Service
@Slf4j
public class RoleCmsServiceImp extends AbsCmsService<RoleRequest, RoleResponse, Role, Integer, RoleRepositoryImp, RoleMapper>
                                implements IRoleCmsService{

    @Autowired
    RoleRepositoryImp roleRepositoryImp;

    @Autowired
    RolePermisionRepositoryImp rolePermisionRepositoryImp;

    @Override
    protected String getPermissionCode() {
        return "role";
    }

    public RoleCmsServiceImp(RoleRepositoryImp repositoryImp, RoleMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }



    @Override
    public Page<RoleResponse> search(SearchRequest searchRequest) {
        List<Role> roles = repository.search(searchRequest);
        List<Integer> roleIds = CollectionUtils.extractField(roles, Role::getId);
        Long totalCount = repository.count(searchRequest);
        List<RolePermission> rolePermissions = rolePermisionRepositoryImp.getByRoleIds(roleIds);
        Map<Integer, Permission> permissionMap = repository.getPermissionsByRoleIds(roleIds);
        List<RoleResponse> roleResponses = mapper.toResponses(roles, rolePermissions, permissionMap);

        return new Page<RoleResponse>()
                .setTotal(totalCount)
                .setItems(roleResponses);
    }

    @Override
    public RoleResponse findById(Integer id, Authentication authentication) {
        try {
            Set<String> actions = checkPermissionGet(authentication);
            Optional<Role> role = repository.findById(id);

            if (role.isEmpty()){
                throw new ApiException(ROLE_NOT_FOUND);
            }

            List<Permission> permissions = repository.getPermissions(id);
            RoleResponse response = mapper.toResponse(role.get(), permissions);
            response.setItemPermissions(actions);
            return response;

        } catch (ApiException e){
            log.error("[ERROR] findById {} " + e.getMessage());
            throw new ApiException(ROLE_NOT_FOUND);
        }
    }

    @Override
    public MessageResponse updatePermissions(RolePermissionRequest request) {
        List<Permission> oldPermissions = repository.getPermissions(request.getId());
        return updatePermissions(request, oldPermissions);
    }

    @Override
    public MessageResponse updatePermissions(RolePermissionRequest request, List<Permission> oldPermissions) {
        Set<Integer> perIds = CollectionUtils.collectToSet(oldPermissions, Permission::getId);

        List<RolePermission> newRolePermission = request.getPermissionIds().stream()
                .filter(id -> !perIds.contains(id))
                .map(perId -> new RolePermission()
                        .setRoleId(request.getId())
                        .setPerId(perId))
                .collect(Collectors.toList());

        List<Integer> removeList = perIds.stream()
                .filter(perId -> !request.getPermissionIds().contains(perId))
                .collect(Collectors.toList());

        rolePermisionRepositoryImp.insert(newRolePermission);
        rolePermisionRepositoryImp.deleteByIds(removeList, request.getId());

        return new MessageResponse("update permission success!");
    }
}
