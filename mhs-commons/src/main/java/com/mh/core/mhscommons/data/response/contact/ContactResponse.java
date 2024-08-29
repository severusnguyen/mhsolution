package com.mh.core.mhscommons.data.response.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mh.core.mhscommons.data.model.Social;
import com.mh.core.mhscommons.data.response.BaseResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactResponse extends BaseResponse {
    private Integer id;
    private String seoId;
    private String address;
    private String email;
    private String phoneNumber;
    private List<Social> socials;
}
