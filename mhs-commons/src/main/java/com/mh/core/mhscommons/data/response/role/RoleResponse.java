package com.mh.core.mhscommons.data.response.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.response.BaseResponse;
import com.mh.core.mhscommons.data.response.permission.PermissionResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse extends BaseResponse {
    private Integer id;
    private String roleName;
    private String roleCode;
    private String description;
    private LocalDateTime deletedAt;
    private List<PermissionResponse> permisions;
}
