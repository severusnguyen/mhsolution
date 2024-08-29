package com.mh.core.mhscommons.data.mappers.user;

import com.mh.core.mhscommons.data.mappers.BaseMap;
import com.mh.core.mhscommons.data.mappers.role.RoleMapper;
import com.mh.core.mhscommons.data.response.user.UserRequest;
import com.mh.core.mhscommons.data.response.user.UserResponse;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.pojos.User;
import com.mh.core.mhscommons.data.tables.pojos.UserRole;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper extends BaseMap<UserRequest, UserResponse, User> {

    @Autowired
    private RoleMapper roleMapper;

    public abstract UserResponse toResponse(User user, @Context List<Role> roles);

    @AfterMapping
    protected void afterResponse(@MappingTarget UserResponse response, @Context List<Role> roles) {
        response.setRoles(roleMapper.toResponses(roles));

    }

    public List<UserResponse> toResponses(List<User> users,
                                          @Context List<UserRole> userRole,
                                          @Context Map<Integer, Role> roleMap) {
        Map<Integer, List<Role>> userRoleMap = userRole.stream()
                .filter(rp -> roleMap.containsKey(rp.getRoleId()))
                .collect(groupingBy(
                        UserRole::getUserId,
                        mapping(rp -> roleMap.get(rp.getRoleId()), toList())));
        return users.stream()
                .map(user -> toResponse(user, userRoleMap.getOrDefault(user.getId(), new ArrayList<>())))
                .collect(Collectors.toList());
    }

    public void addRole(UserResponse response, List<Role> roles) {
        response.setRoles(roleMapper.toResponses(roles));
    }

}
