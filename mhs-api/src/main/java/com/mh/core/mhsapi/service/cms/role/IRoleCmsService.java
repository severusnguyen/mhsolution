package com.mh.core.mhsapi.service.cms.role;

import com.mh.core.mhscommons.data.request.role_permission.RolePermissionRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.role.RoleResponse;
import com.mh.core.mhscommons.data.tables.pojos.Permission;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IRoleCmsService {
    RoleResponse findById(Integer id, Authentication authentication);
    MessageResponse updatePermissions(RolePermissionRequest request);
    MessageResponse updatePermissions(RolePermissionRequest request, List<Permission> oldPermissions);
}
