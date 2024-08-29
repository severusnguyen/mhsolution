package com.mh.core.mhsrepository.repository.user;

import com.mh.core.mhscommons.data.Tables;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.pojos.User;
import com.mh.core.mhscommons.data.tables.records.UserRecord;
import com.mh.core.mhsrepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImp extends AbsRepository<UserRecord, User, Integer> implements IUserRepository{

    @Override
    protected TableImpl<UserRecord> getTable() {
        return Tables.USER;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return dslContext.selectFrom(Tables.USER)
                .where(Tables.USER.EMAIL.eq(username).or(Tables.USER.PHONE.eq(username)))
                .fetchOptionalInto(User.class);
    }

    @Override
    public List<String> findRoleNameByUserId(int id) {
        return dslContext.select(Tables.ROLE.ROLE_NAME)
                .from(Tables.ROLE)
                .join(Tables.USER_ROLE).on(Tables.ROLE.ID.eq(Tables.USER_ROLE.ROLE_ID))
                .where(Tables.USER_ROLE.USER_ID.eq(id))
                .fetchInto(String.class);
    }

    @Override
    public List<Role> getRoles(Integer userId) {
        return dslContext.select(Tables.ROLE.ROLE_NAME)
                .from(Tables.ROLE)
                .join(Tables.USER_ROLE)
                .on(Tables.ROLE.ID.eq(Tables.USER_ROLE.ROLE_ID))
                .join(Tables.USER)
                .on(Tables.USER.ID.eq(Tables.USER_ROLE.USER_ID))
                .where(Tables.USER.ID.eq(userId))
                .fetchInto(Role.class);
    }

    @Override
    public Map<Integer, Role> getRoleByUserIds(List<Integer> userIds) {
        return dslContext.select(Tables.ROLE.ID, Tables.ROLE.ROLE_NAME, Tables.ROLE.ROLE_CODE, Tables.ROLE.DESCRIPTION)
                .from(Tables.ROLE)
                .join(Tables.USER_ROLE)
                .on(Tables.ROLE.ID.eq(Tables.USER_ROLE.ROLE_ID))
                .join(Tables.USER)
                .on(Tables.USER.ID.eq(Tables.USER_ROLE.USER_ID))
                .where(Tables.USER.ID.in(userIds))
                .fetchMap(Tables.ROLE.ID, Role.class);
    }

}
