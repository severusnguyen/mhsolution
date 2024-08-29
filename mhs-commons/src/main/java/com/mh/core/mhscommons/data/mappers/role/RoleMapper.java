package com.mh.core.mhscommons.data.mappers.role;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.mappers.permission.PermissionMapper;
import com.mh.core.mhscommons.data.request.role.RoleRequest;
import com.mh.core.mhscommons.data.response.role.RoleResponse;
import com.mh.core.mhscommons.data.tables.pojos.Permission;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.pojos.RolePermission;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Mapper(componentModel = "spring")
public abstract class RoleMapper extends BaseMap<RoleRequest, RoleResponse, Role> {

    @Autowired
    PermissionMapper permissionMapper;

    public abstract RoleResponse toResponse(Role role, @Context List<Permission> permissions);

    public List<RoleResponse> toResponses(List<Role> roles,
                                          @Context List<RolePermission> rolePermissions,
                                          @Context Map<Integer, Permission> permissionMap){

        Map<Integer, List<Permission>> rolePermissionMap = rolePermissions.stream()
                .filter(rp -> permissionMap.containsKey(rp.getPerId()))
                .collect(groupingBy(
                        RolePermission::getRoleId,
                        mapping(rp -> permissionMap.get(rp.getPerId()), toList())));
        return roles.stream()
                .map(role -> toResponse(role, rolePermissionMap.getOrDefault(role.getId(), new ArrayList<>())))
                .collect(Collectors.toList());
    }

    public void addPermissions(RoleResponse response, List<Permission> permisions) {
        response.setPermisions(permissionMapper.toResponses(permisions));
    }

    @AfterMapping
    protected void afterResponse(@MappingTarget RoleResponse response, @Context List<Permission> permissions){
        response.setPermisions(permissionMapper.toResponses(permissions));
    }
}
