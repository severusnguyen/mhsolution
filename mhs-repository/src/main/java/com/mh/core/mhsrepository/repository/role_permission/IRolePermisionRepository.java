package com.mh.core.mhsrepository.repository.role_permission;

import com.mh.core.mhscommons.data.tables.pojos.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRolePermisionRepository {
    List<String> getPermissions(String permisionCode, List<String> roles);
    List<RolePermission> getByRoleIds(List<Integer> roleIds);
    Integer deleteByIds(List<Integer> perIds, Integer roleId);
}
