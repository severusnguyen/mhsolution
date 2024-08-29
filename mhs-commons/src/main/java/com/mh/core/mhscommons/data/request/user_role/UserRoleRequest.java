package com.mh.core.mhscommons.data.request.user_role;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserRoleRequest {
    private Integer id;
    private List<Integer> roleIds;
}
