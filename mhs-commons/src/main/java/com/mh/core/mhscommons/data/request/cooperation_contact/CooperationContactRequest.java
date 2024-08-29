package com.mh.core.mhscommons.data.request.cooperation_contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CooperationContactRequest {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String phoneName;
    private String email;
    private String about;
    private Integer productId;
    private Integer createBy;
    private Integer deleteBy;
}
