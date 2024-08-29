package com.mh.core.mhsapi.service.cms.user;

import com.mh.core.mhscommons.data.request.change_password.ChangePasswordRequest;
import com.mh.core.mhscommons.data.request.user_role.UserRoleRequest;
import com.mh.core.mhscommons.data.response.MessageResponse;
import com.mh.core.mhscommons.data.response.user.UserResponse;
import com.mh.core.mhscommons.data.tables.pojos.Role;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserCmsService {
    UserResponse findById(Integer id, Authentication authentication);
    MessageResponse updateRole(UserRoleRequest request);
    MessageResponse updateRole(UserRoleRequest request, List<Role> oldRoles);
    MessageResponse changePassword(ChangePasswordRequest request);
}
