package com.mh.core.mhsrepository.repository.permission;

import com.mh.core.mhscommons.data.tables.pojos.Permission;

import java.util.List;

public interface IPermissionRepository {
    List<Permission> getPermissionById(List<Integer> perIds);
    List<Permission> getAllPermission();
}
