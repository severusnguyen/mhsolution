package com.mh.core.mhsrepository.repository.user_role;

import com.mh.core.mhscommons.data.Tables;
import com.mh.core.mhscommons.data.tables.pojos.UserRole;
import com.mh.core.mhscommons.data.tables.records.UserRoleRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleRepositoryImp extends AbsRepository<UserRoleRecord, UserRole, Integer> {

    @Override
    protected TableImpl<UserRoleRecord> getTable() {
        return Tables.USER_ROLE;
    }

    public List<String> getListRoleName(Integer user_id) {
        return dslContext.select(Tables.ROLE.ROLE_NAME)
                .from(Tables.USER_ROLE)
                .join(Tables.ROLE)
                .on(Tables.USER_ROLE.ROLE_ID.eq(Tables.ROLE.ID))
                .where(Tables.USER_ROLE.USER_ID.eq(user_id))
                .fetchInto(String.class);
    }
    public List<UserRole> getByUserIds (List<Integer> userIds) {
        return dslContext.selectFrom(Tables.USER_ROLE)
                .where(Tables.USER_ROLE.USER_ID.in(userIds))
                .fetchInto(UserRole.class);
    }
    public Integer deleteByIds(List<Integer> roleIds, Integer userId) {
        return dslContext.delete(Tables.USER_ROLE)
                .where(Tables.USER_ROLE.ROLE_ID.in(roleIds).and(Tables.USER_ROLE.USER_ID.eq(userId)))
                .execute();
    }
}
