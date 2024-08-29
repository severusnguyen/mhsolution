package com.mh.core.mhsrepository.repository.role_permission;

import com.mh.core.mhscommons.data.Tables;
import com.mh.core.mhscommons.data.tables.pojos.RolePermission;
import com.mh.core.mhscommons.data.tables.records.RolePermissionRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolePermisionRepositoryImp extends AbsRepository<RolePermissionRecord, RolePermission, Integer>
                                        implements IRolePermisionRepository{

    @Override
    protected TableImpl<RolePermissionRecord> getTable() {
        return Tables.ROLE_PERMISSION;
    }

    @Override
    public List<String> getPermissions(String permisionCode, List<String> roles) {
        return dslContext.select(Tables.PERMISSION.ACTION_CODE)
                .from(Tables.PERMISSION)
                .join(Tables.ROLE_PERMISSION)
                .on(Tables.PERMISSION.ID.eq(Tables.ROLE_PERMISSION.PER_ID))
                .join(Tables.ROLE)
                .on(Tables.ROLE.ID.eq(Tables.ROLE_PERMISSION.ROLE_ID))
                .where(Tables.ROLE.ROLE_NAME.in(roles).and(Tables.PERMISSION.ACTION_CODE.like(permisionCode)))
                .fetchInto(String.class);
    }

    @Override
    public List<RolePermission> getByRoleIds(List<Integer> roleIds) {
        return dslContext.selectFrom(Tables.ROLE_PERMISSION)
                .where(Tables.ROLE_PERMISSION.ROLE_ID.in(roleIds))
                .fetchInto(RolePermission.class);
    }

    @Override
    public Integer deleteByIds(List<Integer> perIds, Integer roleId) {
        return dslContext.delete(Tables.ROLE_PERMISSION)
                .where(Tables.ROLE_PERMISSION.PER_ID.in(perIds).and(Tables.ROLE_PERMISSION.ROLE_ID.eq(roleId)))
                .execute();
    }
}
