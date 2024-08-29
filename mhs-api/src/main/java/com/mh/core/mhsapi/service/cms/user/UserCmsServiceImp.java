package com.mh.core.mhsapi.service.cms.user;

import com.mh.core.mhsapi.service.cms.AbsCmsService;
import com.mh.core.mhscommons.data.constant.message.user.UserConstant;
import com.mh.core.mhscommons.data.mappers.user.UserMapper;
import com.mh.core.mhscommons.data.model.SearchRequest;
import com.mh.core.mhscommons.data.model.paging.Page;
import com.mh.core.mhscommons.data.request.change_password.ChangePasswordRequest;
import com.mh.core.mhscommons.data.request.user_role.UserRoleRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.user.UserRequest;
import com.mh.core.mhscommons.data.response.user.UserResponse;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import com.mh.core.mhscommons.data.tables.pojos.User;
import com.mh.core.mhscommons.data.tables.pojos.UserRole;
import com.mh.core.mhscommons.utils.CollectionUtils;
import com.mh.core.mhsconfig.config.exception.ApiException;
import com.mh.core.mhsrepository.repository.user.UserRepositoryImp;
import com.mh.core.mhsrepository.repository.user_role.UserRoleRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserCmsServiceImp extends AbsCmsService<UserRequest, UserResponse, User, Integer, UserRepositoryImp, UserMapper>
                                implements IUserCmsService{

    @Autowired
    UserRoleRepositoryImp userRoleRepositoryImp;

    @Override
    protected String getPermissionCode() {
        return "user";
    }

    public UserCmsServiceImp(UserRepositoryImp repositoryImp, UserMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Override
    public Page<UserResponse> search(SearchRequest searchRequest) {
        List<User> users = repository.search(searchRequest);
        List<Integer> userIds = CollectionUtils.extractField(users, User::getId);
        Long totalCount = repository.count(searchRequest);
        List<UserRole> userRoles = userRoleRepositoryImp.getByUserIds(userIds);
        Map<Integer, Role> roleMap = repository.getRoleByUserIds(userIds);
        List<UserResponse> responses = mapper.toResponses(users, userRoles, roleMap);

        return new Page<UserResponse>()
                .setTotal(totalCount)
                .setItems(responses);
    }

    @Override
    public UserResponse findById(Integer id, Authentication authentication) {
        try {
            Set<String> actions = checkPermissionGet(authentication);
            Optional<User> user = repository.findById(id);
            List<Role> roles = repository.getRoles(id);

            if (user.isEmpty()){
                throw new ApiException(UserConstant.USER_NOT_FOUND);
            }

            UserResponse response = mapper.toResponse(user.get(), roles);
            response.setItemPermissions(actions);
            return response;

        } catch (ApiException e){
            log.error("[ERROR] findById {} " + e.getMessage());
            throw new ApiException(UserConstant.USER_NOT_FOUND);
        }

    }

    @Override
    public MessageResponse updateRole(UserRoleRequest request) {
        List<Role> oldRoles = repository.getRoles(request.getId());
        return updateRole(request, oldRoles);
    }

    @Override
    public MessageResponse updateRole(UserRoleRequest request,
                                       List<Role> oldRoles) {
        Set<Integer> roleIds = CollectionUtils.collectToSet(oldRoles, Role::getId);

        List<UserRole> newUserRole = request.getRoleIds().stream()
                .filter(id -> !roleIds.contains(id))
                .map(roleId -> new UserRole()
                        .setUserId(request.getId())
                        .setRoleId(roleId))
                .collect(Collectors.toList());

        List<Integer> removeList = roleIds.stream()
                .filter(roleId -> !request.getRoleIds().contains(roleId))
                .collect(Collectors.toList());

        userRoleRepositoryImp.insert(newUserRole);
        userRoleRepositoryImp.deleteByIds(removeList, request.getId());

        return new MessageResponse("update role success");
    }

    @Override
    public MessageResponse changePassword(ChangePasswordRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = repository.findById(request.getId()).orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND.value()));

        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            repository.update(user, user.getId());
            return new MessageResponse("Success");
        } else {
            throw new ApiException("Mật khẩu cũ không đúng", HttpStatus.OK.value());
        }
    }
}
