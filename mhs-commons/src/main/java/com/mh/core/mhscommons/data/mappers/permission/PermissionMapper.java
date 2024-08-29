package com.mh.core.mhscommons.data.mappers.permission;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.request.permission.PermissionRequest;
import com.mh.core.mhscommons.data.response.permission.PermissionResponse;
import com.mh.core.mhscommons.data.tables.pojos.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PermissionMapper extends BaseMap<PermissionRequest, PermissionResponse, Permission> {
}
