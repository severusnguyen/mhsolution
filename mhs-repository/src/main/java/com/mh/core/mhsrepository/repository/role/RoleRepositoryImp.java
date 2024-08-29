package com.mh.core.mhsrepository.repository.role;

import com.mh.core.mhscommons.data.Tables;
import com.mh.core.mhscommons.data.tables.pojos.Permission;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.records.RoleRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class RoleRepositoryImp extends AbsRepository<RoleRecord, Role, Integer> implements IRoleRepository {

    @Override
    protected TableImpl<RoleRecord> getTable() {
        return Tables.ROLE;
    }


    public List<Role> findAllById(Collection<Integer> ids) {
        return dslContext.selectFrom(Tables.ROLE)
                .where(Tables.ROLE.ID.in(ids))
                .fetchInto(Role.class);
    }

    public List<Permission> getPermissions(Integer roleId) {
        return dslContext.select(Tables.PERMISSION.ID, Tables.PERMISSION.ACTION_NAME, Tables.PERMISSION.ACTION_CODE)
                .from(Tables.PERMISSION)
                .join(Tables.ROLE_PERMISSION)
                .on(Tables.PERMISSION.ID.eq(Tables.ROLE_PERMISSION.PER_ID))
                .join(Tables.ROLE)
                .on(Tables.ROLE.ID.eq(Tables.ROLE_PERMISSION.ROLE_ID))
                .where(Tables.ROLE.ID.eq(roleId))
                .fetchInto(Permission.class);
    }

    public Map<Integer, Permission> getPermissionsByRoleIds(List<Integer> roleIds) {
        return dslContext
                .select(Tables.PERMISSION.ID, Tables.PERMISSION.ACTION_NAME, Tables.PERMISSION.ACTION_CODE)
                .from(Tables.PERMISSION)
                .join(Tables.ROLE_PERMISSION)
                .on(Tables.PERMISSION.ID.eq(Tables.ROLE_PERMISSION.PER_ID))
                .join(Tables.ROLE)
                .on(Tables.ROLE.ID.eq(Tables.ROLE_PERMISSION.ROLE_ID))
                .where(Tables.ROLE.ID.in(roleIds))
                .fetchMap(Tables.PERMISSION.ID, Permission.class);
    }
}
