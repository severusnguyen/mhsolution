package com.mh.core.mhscommons.data.request.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionRequest {
    private Integer id;
    private String actionName;
    private String actionCode;
    private Boolean checkAction;
    private LocalDateTime deletedAt;
    private String description;
}
