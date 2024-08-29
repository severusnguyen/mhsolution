package com.mh.core.mhscommons.data.response.user;

import com.mh.core.mhscommons.data.response.BaseResponse;
import com.mh.core.mhscommons.data.response.role.RoleResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponse extends BaseResponse {
    private Integer id;
    private String name;
    private String alias;
    private String avatar;
    private String phone;
    private String email;
    private String empCode;
    private LocalDateTime regisDate;
    private Short gender;
    private String facebookLink;
    private String role;
    private String note;
    private List<RoleResponse> roles;
}
