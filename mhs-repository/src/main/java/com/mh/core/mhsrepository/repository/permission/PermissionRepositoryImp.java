package com.mh.core.mhsrepository.repository.permission;

import com.mh.core.mhscommons.data.Tables;
import com.mh.core.mhscommons.data.tables.pojos.Permission;
import com.mh.core.mhscommons.data.tables.records.PermissionRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionRepositoryImp extends AbsRepository<PermissionRecord, Permission, Integer> implements IPermissionRepository{

    @Override
    protected TableImpl<PermissionRecord> getTable() {
        return Tables.PERMISSION;
    }

    @Override
    public List<Permission> getPermissionById(List<Integer> perIds) {
        return dslContext.select(Tables.PERMISSION.ID, Tables.PERMISSION.ACTION_NAME, Tables.PERMISSION.ACTION_CODE, Tables.PERMISSION.DESCRIPTION)
                .from(Tables.PERMISSION)
                .where(Tables.PERMISSION.ID.in(perIds))
                .fetchInto(Permission.class);
    }

    @Override
    public List<Permission> getAllPermission(){
        return dslContext.selectFrom(Tables.PERMISSION)
                .fetchInto(Permission.class);
    }
}
