package com.mh.core.mhsrepository.repository.user;

import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.pojos.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findUserByUsername(String username);
    List<String> findRoleNameByUserId(int id);
    List<Role> getRoles(Integer userId);
    Map<Integer, Role> getRoleByUserIds(List<Integer> userIds);
}
